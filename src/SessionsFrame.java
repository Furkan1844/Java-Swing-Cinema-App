import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionsFrame extends JFrame implements ActionListener {


    JButton buttonSubmit = new JButton();
    JButton buttonCancel = new JButton();

    SessionsFrame(){

        this.setTitle("Movie");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setVisible(true);


        setupButton(buttonSubmit, "Submit", 0, 715, 400, 50);
        setupButton(buttonCancel, "Cancel", 400, 715, 400, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if (e.getSource()==buttonSubmit){} // TODO: when clicked to the submit button, write record to the db's Tickets Table
        if (e.getSource()==buttonCancel){
            this.dispose();
            new MainFrame();
        }
    }

    // create button according to intended size
    private void setupButton(JButton button, String text, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setText(text);
        button.setFocusable(false);
        button.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createBevelBorder(0));
        button.addActionListener(this);
        this.add(button);
    }

    // create label according to intended size
    private void setupLabel(JLabel label, String label_name, int x, int y, int width, int height){
        label.setText(label_name);
        label.setBounds(x, y, width, height);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createBevelBorder(0));
        label.setOpaque(true);
        this.add(label);
    }

    // create text partition according to intended size
    private void setupText(JTextField textField, int x, int y, int width, int height) {
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        this.add(textField);
    }
}
