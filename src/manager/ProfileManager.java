package manager;

import model.Profile;
import model.Product;
import data.DataUtil;

import java.util.Map;

public class ProfileManager {
    private Map<String, Profile> profiles;

    public ProfileManager() {
        this.profiles = DataUtil.loadProfiles();
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
            DataUtil.saveProfiles(profiles);
        }
    }

    public void deleteProfile(String username) {
        profiles.remove(username);
        DataUtil.saveProfiles(profiles);
    }

    public void addProfile(Profile profile) {
        profiles.put(profile.getUsername(), profile);
        DataUtil.saveProfiles(profiles);
    }

    public void addPurchaseToProfile(String username, Product product) {
        if (profiles.containsKey(username)) {
            profiles.get(username).addPurchasedProduct(product);
            DataUtil.saveProfiles(profiles);
        }
    }

    public void createProfile(String username, String name, String phone, String password) {
        if (!profiles.containsKey(username)) {
            Profile profile = new Profile(username, name, phone, password);
            profiles.put(username, profile);
            DataUtil.saveProfiles(profiles);
        }
    }

    public boolean removePurchaseFromProfile(String username, Product product) {
        if (profiles.containsKey(username)) {
            Profile profile = profiles.get(username);
            boolean removed = profile.removePurchasedProduct(product);
            if (removed) {
                DataUtil.saveProfiles(profiles);
            }
            return removed;
        }
        return false;
    }

}
