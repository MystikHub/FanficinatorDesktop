package fanficinate;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIStart {

	private JFrame frmFanficinatorBeta;
	private JTextField textField;
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
		frmFanficinatorBeta.setTitle("Fanficinator 0.0.3 beta");
		frmFanficinatorBeta.setBounds(100, 100, 450, 300);
		frmFanficinatorBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image programIcon = Toolkit.getDefaultToolkit().getImage("res/fanfiction_logo.png");
		frmFanficinatorBeta.setIconImage(programIcon);
		
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
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your fanfic below or enter the file URL here:");
		lblPleaseEnterYour.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblPleaseEnterYour);
		
		textField = new JTextField();
		textField.setToolTipText("File URL");
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Path filePath = Paths.get(textField.getText());
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
				String newFanfic = fanficObject.Fanficinate();
				
				JFrame resultWindow = new JFrame("Processed Fanfic");
				resultWindow.setVisible(true);
				resultWindow.setBounds(frmFanficinatorBeta.getX() + 100, frmFanficinatorBeta.getY() + 100, 300, 200);
				TextArea resultTextArea = new TextArea(newFanfic);
				resultTextArea.setEditable(false);
				resultTextArea.setBounds(0, 0, 300, 300);
				resultWindow.getContentPane().add(resultTextArea, BorderLayout.NORTH);
			}
		});
		panel.add(btnGo);
		
		final TextArea textArea = new TextArea();
		frmFanficinatorBeta.getContentPane().add(textArea, BorderLayout.WEST);
		
		JButton btnFanficinate = new JButton("Fanficinate");
		btnFanficinate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String fanfic = textArea.getText();
				Fanfic fanficObject = new Fanfic();
				fanficObject.mainJFrame = frmFanficinatorBeta;
				fanficObject.fanfic = fanfic;
				String newFanfic = fanficObject.Fanficinate();
				
				JFrame resultWindow = new JFrame("Processed Fanfic");
				resultWindow.setVisible(true);
				resultWindow.setBounds(frmFanficinatorBeta.getX() + 100, frmFanficinatorBeta.getY() + 100, 300, 200);
				TextArea resultTextArea = new TextArea(newFanfic);
				resultTextArea.setEditable(false);
				resultTextArea.setBounds(0, 0, 300, 300);
				resultWindow.getContentPane().add(resultTextArea, BorderLayout.NORTH);
			}
		});
		frmFanficinatorBeta.getContentPane().add(btnFanficinate, BorderLayout.SOUTH);
	}
}
