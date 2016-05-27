package fanficinate;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GUIStart {

	private JFrame frmFanficinatorBeta;
	fileExplorer fileExplorer = new fileExplorer();
	File path = new File("New");
	public int complexity;
	final TextArea textArea = new TextArea();
	Fanfic fanficObject = new Fanfic();

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
		
		JMenuBar menuBar = new JMenuBar();
		frmFanficinatorBeta.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFanficination = new JMenuItem("New Fanficination");
		mnFile.add(mntmNewFanficination);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			readFile();
		}});
		
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
			
		frmFanficinatorBeta.getContentPane().add(textArea, BorderLayout.WEST);
		JButton btnFanficinate = new JButton("Fanficinate");
		btnFanficinate.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				String fanfic = textArea.getText();
				fanficObject.fanfic = fanfic;
				String newFanfic = fanficObject.Fanficinate();
				displayFanfic(newFanfic);
			}
		});
		frmFanficinatorBeta.getContentPane().add(btnFanficinate, BorderLayout.SOUTH);	
	}
	
	
	private void displayFanfic(String fic) {
		JFrame resultWindow = new JFrame("Processed Fanfic");
		resultWindow.setVisible(true);
		resultWindow.setBounds(frmFanficinatorBeta.getX() + 100, frmFanficinatorBeta.getY() + 100, 300, 200);
		TextArea resultTextArea = new TextArea(fic);
		resultTextArea.setEditable(false);
		resultTextArea.setBounds(0, 0, 300, 300);
		resultWindow.getContentPane().add(resultTextArea, BorderLayout.NORTH);
	}
	
	
	private void readFile() {	
		String filePath = (fileExplorer.makeChooser()).getAbsolutePath();
		StringBuilder str = new StringBuilder();
		String line;
		try {
			BufferedReader ois = new BufferedReader(new FileReader(filePath));
			while ((line = ois.readLine()) != null) {
				str.append(line);
			}
			ois.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		fanficObject.fanfic = str.toString();
		String newFanfic = fanficObject.Fanficinate();
		displayFanfic(newFanfic);
	}
}