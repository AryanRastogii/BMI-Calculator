import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, registerBtn;
    UserManager userManager;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        userManager = new UserManager();

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel("Password:")); add(passwordField);
        add(loginBtn); add(registerBtn);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        if (e.getSource() == loginBtn) {
            User user = userManager.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // close login window
                new BMICalculator(user, userManager);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        } else if (e.getSource() == registerBtn) {
            boolean success = userManager.register(username, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful! Please log in.");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            }
        }
    }
}
