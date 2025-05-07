package manager;
import model.Profile;
import model.Product;
import java.util.Map;
import java.io.*;
import java.util.HashMap;


public class ProfileManager {
    private static final String DATA_DIR     = "data";
    private static final String PROFILE_FILE = DATA_DIR + File.separator + "profiles.txt";
    private Map<String, Profile> profiles;

    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            new File(PROFILE_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProfileManager() {
        this.profiles = loadProfiles();
    }





    public void saveProfiles(Map<String, Profile> profiles) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROFILE_FILE))) {
            for (Profile p : profiles.values()) {
                String input = p.getPurchasedProducts().toString();
                if(input != ""){
                    writer.println(p.getUsername() + ";" + p.getName() + ";" + p.getPhoneNumber() + ";" + p.getPassword() + ";" + input);
                }else{
                    writer.println(p.getUsername() + ";" + p.getName() + ";" + p.getPhoneNumber() + ";" + p.getPassword());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Profile> loadProfiles() {
        HashMap<String, Profile> profiles = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    profiles.put(parts[0], new Profile(parts[0], parts[1], parts[2], parts[3]));
                }else if(parts.length == 5){
            
                    Map<String, Integer> input = convertStringToMap(parts[4]);
                    profiles.put(parts[0], new Profile(parts[0], parts[1], parts[2], parts[3], input));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    public Map<String, Integer> convertStringToMap(String input) {
        Map<String, Integer> map = new HashMap<>();

        if (input == null || input.isEmpty()) {
            return map; // Return empty map for null or empty input
        }

        String input2 = input.replace("{", "");
        String input3 = input2.replace("}", "");

        String[] entries = input3.split(",");
        for (String entry : entries) {
            String[] parts = entry.split("=", 2); // Limit split to 2 parts
            if (parts.length == 2) {
                String key = parts[0].trim();
                Integer value = Integer.valueOf(parts[1].trim());
                 map.put(key, value);
            }
        }
        return map;
    }



    public Profile getProfile(String username) {
        return profiles.get(username);
    }

    public boolean validateLogin(String username, String password) {
        Profile profile = profiles.get(username);
        return profile != null && profile.getPassword().equals(password);
    }

    public boolean profileExists(String username) {
        return profiles.containsKey(username);
    }

    public void updateProfile(String username, String name, String phoneNumber) {
        if (profiles.containsKey(username)) {
            Profile profile = profiles.get(username);
            profile.setName(name);
            profile.setPhoneNumber(phoneNumber);
            saveProfiles(profiles);
        }
    }

    public void deleteProfile(String username) {
        profiles.remove(username);
        saveProfiles(profiles);
    }

    public void addProfile(Profile profile) {
        profiles.put(profile.getUsername(), profile);
        saveProfiles(profiles);
    }

    public void addPurchaseToProfile(String username, Product product) {
        if (profiles.containsKey(username)) {
            profiles.get(username).addPurchasedProduct(product);
            saveProfiles(profiles);
            this.profiles = loadProfiles();
        }
    }

    public void createProfile(String username, String name, String phone, String password) {
        if (!profiles.containsKey(username)) {
            Profile profile = new Profile(username, name, phone, password);
            profiles.put(username, profile);
            saveProfiles(profiles);
        }
    }

    public boolean removePurchaseFromProfile(String username, Product product) {
        this.profiles = loadProfiles();
        if (profiles.containsKey(username)) {
            Profile profile = profiles.get(username);
            boolean removed = profile.removePurchasedProduct(product);
            if (removed) {
                saveProfiles(profiles);
            }
            return removed;
        }
        return false;
    }

}
