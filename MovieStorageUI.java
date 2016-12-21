
/*
 * 
 * CLASS: MovieStorageUI
 * AUTHOR: Adnan Alihodzic
 * DESCRIPTION: Creates the UI for the databases and it's operations
 * 
 * 
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MovieStorageUI extends JFrame {

	private JPanel contentPane;
	DBLogic db = new DBLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieStorageUI frame = new MovieStorageUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MovieStorageUI() throws SQLException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1253, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea dataWindow = new JTextArea();
		dataWindow.setEditable(false);
		dataWindow.setBounds(839, 29, 296, 438);
		contentPane.add(dataWindow);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			// Adds inputs to the movie table
			public void actionPerformed(ActionEvent e) {
				try {
					MovieRegistration movies = new MovieRegistration();
					movies.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addButton.setBounds(287, 191, 97, 25);
		contentPane.add(addButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {

			// Delets inputs from the Movies table
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog(null, "What movie do you want to remove", "Movie deletion",
						JOptionPane.PLAIN_MESSAGE);
				String year = JOptionPane.showInputDialog(null, "From what year", "Movie deletion",
						JOptionPane.PLAIN_MESSAGE);
				int movieYear = Integer.parseInt(year);
				try {
					db.deleteMovie(title, movieYear);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteButton.setBounds(287, 305, 97, 25);
		contentPane.add(deleteButton);

		JButton searchButtpn = new JButton("Search");
		searchButtpn.addActionListener(new ActionListener() {

			// Performes a search on actors and directors
			public void actionPerformed(ActionEvent e) {
				dataWindow.setText(null);
				db.searchResults.clear();
				String searchString = JOptionPane.showInputDialog(null, "Enter what you are looking for ?",
						"Table search", JOptionPane.PLAIN_MESSAGE);
				try {
					db.searchData(searchString);
					for (int i = 0; i <= db.searchResults.size() - 1; i++) {
						dataWindow.append(db.searchResults.get(i) + "\n");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		searchButtpn.setBounds(287, 381, 97, 25);
		contentPane.add(searchButtpn);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {

			// Activation of the edit function
			public void actionPerformed(ActionEvent e) {
				String tableName = JOptionPane.showInputDialog(null, "What table do you want to edit ?", "Table edit",
						JOptionPane.PLAIN_MESSAGE);
				String columnName = JOptionPane.showInputDialog(null, "What column ?", "Table edit",
						JOptionPane.PLAIN_MESSAGE);
				String newValue = JOptionPane.showInputDialog(null, "Set the new value", "Table edit",
						JOptionPane.PLAIN_MESSAGE);
				String idString = JOptionPane.showInputDialog(null, "At what id", "Table edit",
						JOptionPane.PLAIN_MESSAGE);
				try {
					int id = Integer.parseInt(idString);

					db.editData(tableName, columnName, newValue, id);

				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		editButton.setBounds(287, 229, 97, 25);
		contentPane.add(editButton);

		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ActionListener() {

			// Showing of the entries in the tables
			public void actionPerformed(ActionEvent e) {
				dataWindow.setText(null);
				db.types.clear();
				db.actorNames.clear();
				db.actorIds.clear();
				db.directorIds.clear();
				db.directorNames.clear();
				db.genreIds.clear();
				db.movieDescrip.clear();
				db.movieLength.clear();
				db.movieTitles.clear();
				db.movieYears.clear();

				String choice = JOptionPane.showInputDialog(null, "What do you want to display", "Table display",
						JOptionPane.PLAIN_MESSAGE);

				if (choice.equals("actors")) {
					try {
						db.showActors();
						dataWindow.append("IDs" + " " + " " + "Actor names:" + "\n");
						for (int i = 0; i <= db.actorIds.size() - 1; i++) {
							dataWindow.append(db.actorIds.get(i) + " " + db.actorNames.get(i) + "\n");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (choice.equals("directors")) {
					try {
						db.showDirector();
						dataWindow.append("IDs" + " " + " " + "Director names:" + "\n");
						for (int i = 0; i <= db.directorIds.size() - 1; i++) {
							dataWindow.append(db.directorIds.get(i) + " " + db.directorNames.get(i) + "\n");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (choice.equals("genres")) {
					try {
						db.showGenre();
						dataWindow.append("IDs" + " " + " " + "Genres:" + "\n");
						for (int i = 0; i <= db.genreIds.size() - 1; i++) {
							dataWindow.append(db.genreIds.get(i) + " " + db.types.get(i) + "\n");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (choice.equals("movies")) {
					try {
						db.showMovies();
						dataWindow.append(
								"Title" + " " + " " + "Year:" + " " + " " + "Length" + " " + " " + "Comment:" + "\n");
						for (int i = 0; i <= db.movieTitles.size() - 1; i++) {
							dataWindow.append(db.movieTitles.get(i) + " " + db.movieYears.get(i) + " "
									+ db.movieLength.get(i) + " " + db.movieDescrip.get(i) + "\n");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		showButton.setBounds(287, 267, 97, 25);
		contentPane.add(showButton);

		JButton selectionButton = new JButton("Selection");
		selectionButton.addActionListener(new ActionListener() {

			// Performs the select function
			public void actionPerformed(ActionEvent e) {
				dataWindow.setText(null);
				String choice1 = JOptionPane.showInputDialog(null, "What type of selection ? Actor, Director or Genre ",
						"Table selection", JOptionPane.PLAIN_MESSAGE);
				String choice2 = JOptionPane.showInputDialog(null, "Name of actor,director or genre ?",
						"Table selection", JOptionPane.PLAIN_MESSAGE);
				try {
					db.selection(choice1, choice2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i <= db.selectionList.size() - 1; i++) {
					dataWindow.append(db.selectionList.get(i) + "\n");
				}
			}
		});
		selectionButton.setBounds(287, 343, 97, 25);
		contentPane.add(selectionButton);
	}
}
