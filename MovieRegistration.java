/*
 * 
 * CLASS: MovieRegistration
 * AUTHOR: Adnan Alihodzic
 * DESCRIPTION: Creates a window for movie input data
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MovieRegistration extends JFrame {

	private JPanel registration;
	private JTextField titleText;
	private JTextField yearText;
	private JTextField lengthText;
	private JTextField commentText;
	String title = null;
	int year;
	String runningTime = null;
	String comment = null;

	DBLogic db = new DBLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieRegistration frame = new MovieRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MovieRegistration() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 329);
		registration = new JPanel();
		registration.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registration);
		registration.setLayout(null);

		JLabel lblTitle = new JLabel("\t\t\t\tTitle:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(78, 37, 82, 26);
		registration.add(lblTitle);

		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setBounds(403, 39, 82, 26);
		registration.add(lblYear);

		JLabel lblLength = new JLabel("Length:");
		lblLength.setHorizontalAlignment(SwingConstants.CENTER);
		lblLength.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLength.setBounds(614, 37, 92, 26);
		registration.add(lblLength);

		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblComment.setBounds(870, 37, 121, 33);
		registration.add(lblComment);

		titleText = new JTextField();
		titleText.setBounds(88, 76, 286, 33);
		registration.add(titleText);
		titleText.setColumns(10);

		yearText = new JTextField();
		yearText.setBounds(413, 78, 164, 31);
		registration.add(yearText);
		yearText.setColumns(10);

		lengthText = new JTextField();
		lengthText.setBounds(624, 76, 164, 31);
		registration.add(lengthText);
		lengthText.setColumns(10);

		commentText = new JTextField();
		commentText.setBounds(870, 83, 245, 114);
		registration.add(commentText);
		commentText.setColumns(10);

		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {

			// Gets the inputs for the movie database puts the in the input
			// function
			public void actionPerformed(ActionEvent e) {
				convertText();
				try {
					db.insertMovie(title, year, runningTime, comment);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		enterButton.setBounds(306, 173, 179, 57);
		registration.add(enterButton);
	}

	// Gets the text from the different input fields
	private void convertText() {
		title = titleText.getText();
		year = Integer.parseInt(yearText.getText());
		runningTime = lengthText.getText();
		comment = commentText.getText();
	}
}
