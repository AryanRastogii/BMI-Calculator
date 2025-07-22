import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMICalculator extends JFrame implements ActionListener {

    JTextField feetField, inchesField, weightField;
    JButton calculateButton, clearButton;
    JTextArea bmiChart;

    public BMICalculator() {
        setTitle("BMI Calculator");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        
        JLabel feetLabel = new JLabel("Height (feet):");
        feetField = new JTextField();

        JLabel inchesLabel = new JLabel("Height (inches):");
        inchesField = new JTextField();

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightField = new JTextField();

        calculateButton = new JButton("Calculate BMI");
        clearButton = new JButton("Clear");

        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);

        
        bmiChart = new JTextArea();
        bmiChart.setEditable(false);
        bmiChart.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bmiChart.setText(
                "📊 BMI Categories:\n" +
                "----------------------------\n" +
                "Underweight : < 18.5\n" +
                "Normal      : 18.5 – 24.9\n" +
                "Overweight  : 25 – 29.9\n" +
                "Obese       : 30 and above\n"
        );

        
        add(feetLabel);
        add(feetField);
        add(inchesLabel);
        add(inchesField);
        add(weightLabel);
        add(weightField);
        add(calculateButton);
        add(clearButton);
        add(new JLabel("BMI Range Chart:"));
        add(new JScrollPane(bmiChart)); 

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                int feet = Integer.parseInt(feetField.getText());
                int inches = Integer.parseInt(inchesField.getText());
                double weight = Double.parseDouble(weightField.getText());

                if (feet < 0 || inches < 0 || weight <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter valid positive values.");
                    return;
                }

                double totalInches = (feet * 12) + inches;
                double heightInMeters = totalInches * 0.0254;
                double bmi = weight / (heightInMeters * heightInMeters);

                String status;
                if (bmi < 18.5) status = "Underweight";
                else if (bmi < 25) status = "Normal";
                else if (bmi < 30) status = "Overweight";
                else status = "Obese";

                JOptionPane.showMessageDialog(this,
                        String.format("Your BMI is: %.2f\nCategory: %s", bmi, status));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers.");
            }
        } else if (e.getSource() == clearButton) {
            feetField.setText("");
            inchesField.setText("");
            weightField.setText("");
        }
    }

    public static void main(String[] args) {
        new BMICalculator();
    }
}