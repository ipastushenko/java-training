package ipastushenko.training.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AccountController
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
@Controller
@RequestMapping(value="/account")
public class AccountController {
    private Map<Long, Account> accounts = new ConcurrentHashMap<>();
    private Validator validator;

    @Autowired
    public AccountController(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String getCreateForm(Model model) {
        model.addAttribute(new Account());
        return "account/createForm";
    }

    @RequestMapping(value="/availability", method=RequestMethod.GET)
    public @ResponseBody AvailabilityStatus getAvailability(@RequestParam String name) {
        for (Account a : accounts.values()) {
            if (a.getName().equals(name)) {
                return AvailabilityStatus.notAvailable(name);
            }
        }
        return AvailabilityStatus.available();
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Map<String, ?> create(@RequestBody Account account, HttpServletResponse response) {
        Set<ConstraintViolation<Account>> failures = validator.validate(account);
        if (!failures.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return validationMessages(failures);
        }
        accounts.put(account.assignId(), account);
        return Collections.singletonMap("id", account.getId());
    }

    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public @ResponseBody Account get(@PathVariable Long id) {
        Account account = accounts.get(id);
        if (account == null) {
            throw new ResourceNotFoundException(id);
        }
        return account;
    }

    private Map<String, String> validationMessages(Set<ConstraintViolation<Account>> failures) {
        Map<String, String> failureMessages = new HashMap<>();
        for (ConstraintViolation<Account> failure : failures) {
            failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
        }
        return failureMessages;
    }
}
