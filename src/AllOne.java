import java.util.*;


/**
 * A data structure with all O(1) operations
 */
public class AllOne {
    /** Stores the current values of all the keys **/
    private HashMap<String, Integer> values = new HashMap<>();

    /** A set of all keys that have the desired value **/
    private TreeMap<Integer, HashSet<String>> keysByValue = new TreeMap<>();

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int newValue;
        if (values.containsKey(key)) {
            newValue = values.get(key) + 1;
        }
        else {
            newValue = 1;
        }

        migrateKey(key, newValue);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        int minValue = 1;
        if (!values.containsKey(key)) {
            return;
        }

        if (values.get(key) == minValue) {
            // Remove this key from its key set
            HashSet<String> curKeys = keysByValue.get(minValue);
            curKeys.remove(key);
            if (curKeys.isEmpty()) {
                keysByValue.remove(1);
            }
            else {
                keysByValue.put(1, curKeys);
            }

            // Remove this key from the values hashmap
            values.remove(key);
        }
        else {
            if (values.containsKey(key)) {
                int newValue = values.get(key) - 1;
                migrateKey(key, newValue);
            }
        }
    }

    /** Helper function to update tables on migration of a key **/
    private void migrateKey(String key, int newValue) {
        if (values.containsKey(key)) {
            // Remove this key from the old set it belonged to
            HashSet<String> oldSet = keysByValue.get(values.get(key));
            oldSet.remove(key);

            if (oldSet.isEmpty()) {
                keysByValue.remove(values.get(key));
            }
            else {
                keysByValue.put(values.get(key), oldSet);
            }
        }

        // Add this key to a new set with its new value
        HashSet<String> newSet;
        if (keysByValue.containsKey(newValue)) {
            newSet = keysByValue.get(newValue);
        }
        else {
            newSet = new HashSet<>();
        }
        newSet.add(key);
        keysByValue.put(newValue, newSet);

        // Add this keys new value to the values hashmap
        values.put(key, newValue);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (values.isEmpty()) {
            return "";
        }

        HashSet<String> maxKeys = keysByValue.lastEntry().getValue();
        return maxKeys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (values.isEmpty()) {
            return "";
        }

        HashSet<String> minKeys = keysByValue.firstEntry().getValue();
        return minKeys.iterator().next();
    }
}
