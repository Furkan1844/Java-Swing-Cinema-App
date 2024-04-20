import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class MovieFrame extends JFrame implements ActionListener {

    JLabel labelHeader = new JLabel();
    JLabel labelMovieName = new JLabel();
    JLabel labelMovieGenres = new JLabel();
    JLabel labelMovieDuration = new JLabel();
    JLabel labelMovieTopCast = new JLabel();

    JTextField textMovieName = new JTextField();
    JTextField textMovieGenres = new JTextField();
    JTextField textMovieDuration = new JTextField();
    JTextField textMovieTopCast = new JTextField();

    JTextArea textArea = new JTextArea();

    String dbName = "movies.db";
    String jdbcUrl = "jdbc:sqlite:" + dbName;

    JButton buttonSubmit = new JButton();
    JButton buttonCancel = new JButton();

    MovieFrame(){

        this.setTitle("Movie");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setVisible(true);

        setupLabel(labelHeader, "Create Movie", 0, 0, 800, 50);
        setupLabel(labelMovieName, "Movie Name", 0, 50, 400, 50);
        setupLabel(labelMovieGenres, "Genres", 0, 100, 400, 50);
        setupLabel(labelMovieDuration, "Duration", 0, 150, 400, 50);
        setupLabel(labelMovieTopCast, "Top Cast", 0, 200, 400, 50);

        setupText(textMovieName, 400, 50, 400, 50);
        setupText(textMovieGenres, 400, 100, 400, 50);
        setupText(textMovieDuration, 400, 150, 400, 50);
        setupText(textMovieTopCast, 400, 200, 400, 50);

        textArea.setPreferredSize(new Dimension(0,2000));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setAutoscrolls(true);
        textArea.setFont(new Font("Times New Roman", Font.BOLD, 22));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(), BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        JScrollPane scrollFrame = new JScrollPane(textArea);
        scrollFrame.setBounds(0, 250, 800, 465);
        this.add(scrollFrame);

        setupButton(buttonSubmit, "Submit", 0, 715, 400, 50);
        setupButton(buttonCancel, "Cancel", 400, 715, 400, 50);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonSubmit){

            // SQL VERIFICATION, CONNECTION AND WRITE TO DATABASE

            // check if the database file exists in the working directory
            File dbFile = new File(dbName);
            if (!dbFile.exists()) {
                try (Connection connection = DriverManager.getConnection(jdbcUrl);
                     Statement statement = connection.createStatement()) {
                    // create table(s)
                    String createTableQuery = "CREATE TABLE IF NOT EXISTS movies (name TEXT, genres TEXT, duration TEXT, topCast TEXT)";
                    statement.execute(createTableQuery);
                    System.out.println("Database created successfully.");
                } catch (SQLException x) {
                    System.out.println("Error creating SQLite Database");
                    x.printStackTrace();
                    return;
                }
            }

            // INSERT VALUES INTO TABLE

            String insertValues = "INSERT INTO movies(name, genres, duration, topCast) VALUES(?, ?, ?, ?)";

            String movieName = String.valueOf(textMovieName.getText());
            String movieGenres = String.valueOf(textMovieGenres.getText());
            String movieDuration = String.valueOf(textMovieDuration.getText());
            String movieTopCast = String.valueOf(textMovieTopCast.getText());

            try (Connection connection = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement pstmt = connection.prepareStatement(insertValues)) {
                pstmt.setString(1, movieName);
                pstmt.setString(2, movieGenres);
                pstmt.setString(3, movieDuration);
                pstmt.setString(4, movieTopCast);
                pstmt.executeUpdate();
                //TODO: pop-up a message indicating SUCCESS
            }
            catch (SQLException x){
                System.out.println("Error connecting to SQLite Database");
                x.printStackTrace();
            }
        }
        if (e.getSource()==buttonCancel){
            this.dispose();
            new MainFrame();
        }
    }

    /**
    private void updateTextArea(String jdbcUrl) throws SQLException {

        String movies = "SELECT ROWID, * FROM movies";

        Connection connection = DriverManager.getConnection(jdbcUrl);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(movies);

        String labels = ("ID | " + "NAME | " + "GENRES | " + "DURATION | " + "TOP CAST |\n");
        textArea.append(labels);

        while (resultSet.next()) {
            int rowid = resultSet.getInt("ROWID");
            String name = resultSet.getString("name");
            String genres = resultSet.getString("genres");
            String duration = resultSet.getString("duration");
            String topCast = resultSet.getString("topCast");
        }
    }
     **/

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
