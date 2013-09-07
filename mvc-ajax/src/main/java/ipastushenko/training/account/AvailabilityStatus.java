package ipastushenko.training.account;

/**
 * AvailabilityStatus
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class AvailabilityStatus {
    private static final AvailabilityStatus AVAILABILE_INSTANCE = new AvailabilityStatus(true, new String[0]);
    private boolean available;
    private String[] suggestions;

    private AvailabilityStatus(boolean available, String[] suggestions) {
        this.available = available;
        this.suggestions = suggestions;
    }

    public boolean isAvailable() {
        return available;
    }

    public String[] getSuggestions() {
        return suggestions;
    }

    public static AvailabilityStatus available() {
        return AVAILABILE_INSTANCE;
    }

    public static AvailabilityStatus notAvailable(String name) {
        String[] suggestions = new String[3];
        for (int i = 0; i < suggestions.length; i++) {
            suggestions[i] = name + (i + 1);
        }
        return new AvailabilityStatus(false, suggestions);
    }
}
