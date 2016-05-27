package fanficinate;

// Imports
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;

public class GUIStart {

	private JFrame frmFanficinatorBeta;
	private JTextField filePathField;
	// How many passes of fanficination to do on the fanfic
	public int complexity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIStart window = new GUIStart();
					window.frmFanficinatorBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIStart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFanficinatorBeta = new JFrame();
		frmFanficinatorBeta.setTitle("Fanficinator 0.0.4 beta");
		frmFanficinatorBeta.setBounds(100, 100, 614, 353);
		frmFanficinatorBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frmFanficinatorBeta);

		SwingUtilities.updateComponentTreeUI(frmFanficinatorBeta);
		
		// Set the custom icon
		Image programIcon = Toolkit.getDefaultToolkit().getImage("res/fanfiction_logo.png");
		frmFanficinatorBeta.setIconImage(programIcon);
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		frmFanficinatorBeta.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFanficination = new JMenuItem("New Fanficination");
		mnFile.add(mntmNewFanficination);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		// Preferences JFrame
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFrame frmPreferences = new Preferences();
				frmPreferences.setVisible(true);
			}
		});
		mnOptions.add(mntmPreferences);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JPanel panel = new JPanel();
		frmFanficinatorBeta.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		// Fanficinate from file
		JLabel lblPleaseEnterYour = new JLabel("Please enter your fanfic below or enter the file URL here:");
		lblPleaseEnterYour.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblPleaseEnterYour);
		
		filePathField = new JTextField();
		filePathField.setToolTipText("File URL");
		panel.add(filePathField);
		filePathField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Path filePath = Paths.get(filePathField.getText());
				Scanner scanner = null;
				try {
					scanner = new Scanner(filePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				String words = new String();
				while (scanner.hasNext()) {
				    if (scanner.hasNextLine()) {
				        words += scanner.nextLine();
				    } else {
				        scanner.next();
				    }
				}
				Fanfic fanficObject = new Fanfic();
				fanficObject.fanfic = words;
				fanficObject.mainJFrame = frmFanficinatorBeta;
				fanficObject.Fanficinate();
				fanficObject.showResult();
			}
		});
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text files", "txt");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getPath();
			    	filePathField.setText(filePath);
			    }
			}
		});
		panel.add(btnOpen);
		panel.add(btnGo);
		
		// Fanficinate from direct input
		final TextArea textArea = new TextArea();
		textArea.setPreferredSize(new Dimension(599, 300));
		frmFanficinatorBeta.getContentPane().add(textArea, BorderLayout.WEST);
		
		JButton btnFanficinate = new JButton("Fanficinate");
		btnFanficinate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String fanfic = textArea.getText();
				Fanfic fanficObject = new Fanfic();
				fanficObject.mainJFrame = frmFanficinatorBeta;
				fanficObject.fanfic = fanfic;
				fanficObject.showResult();
			}
		});
		frmFanficinatorBeta.getContentPane().add(btnFanficinate, BorderLayout.SOUTH);
	}
}
