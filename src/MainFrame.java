import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener{

    JButton buttonMovie = new JButton();
    JButton buttonHall = new JButton();
    JButton buttonSessions = new JButton();

    MainFrame(){
        this.setTitle("Cinema");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setVisible(true);

        JPanel panelUp = new JPanel();
        panelUp.setBounds(0, 0, 800, 200);
        panelUp.setBackground(Color.GRAY);
        panelUp.setBorder(BorderFactory.createBevelBorder(1));
        this.add(panelUp);

        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(0, 500, 800, 200);
        panelBottom.setBackground(Color.GRAY);
        panelBottom.setBorder(BorderFactory.createBevelBorder(1));
        this.add(panelBottom);

        // very bottom side of frame
        JLabel labelVersion = new JLabel();
        labelVersion.setBounds(0, 705, 800, 55);
        labelVersion.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        labelVersion.setBorder(BorderFactory.createBevelBorder(0));
        labelVersion.setBackground(Color.GRAY);
        labelVersion.setHorizontalAlignment(SwingConstants.CENTER);
        labelVersion.setText("Version 1.0.0");
        this.add(labelVersion);

        setupButton(buttonMovie, "Create Movie", 0, 200, 800, 100);
        setupButton(buttonHall, "Create Hall", 0, 300, 800, 100);
        setupButton(buttonSessions, "Sessions", 0, 400, 800, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonMovie){
            MainFrame.this.dispose();
            new MovieFrame();
        }
        if (e.getSource()==buttonHall){
            MainFrame.this.dispose();
            new HallFrame();
        }
        if (e.getSource()==buttonSessions){
            MainFrame.this.dispose();
            new SessionsFrame();
        }
    }

    // to create button according to intended size
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
}
