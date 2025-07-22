import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private List<String> bmiHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bmiHistory = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void addBmiRecord(String record) {
        bmiHistory.add(record);
    }

    public List<String> getBmiHistory() {
        return bmiHistory;
    }
}
