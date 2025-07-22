import java.io.*;
import java.util.HashMap;

public class UserManager {
    private static final String FILE_NAME = "users.dat";
    private HashMap<String, User> users;

    public UserManager() {
        users = loadUsers();
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        saveUsers();
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (HashMap<String, User>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public void save() {
        saveUsers(); // expose for BMIcalculator to use
    }
}