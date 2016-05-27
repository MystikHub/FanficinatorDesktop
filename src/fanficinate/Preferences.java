package fanficinate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Preferences extends JFrame {

	private JPanel contentPane;
	public int complexity = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preferences frame = new Preferences();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Preferences() {
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 289, 102);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				switch(comboBox.getSelectedItem().toString()) {
					case "1 Pass": {
						complexity = 1;
						break;
					}
					case "2 Passes": {
						complexity = 2;
						break;
					}
					case "3 Passes": {
						complexity = 3;
						break;
					}
					case "4 Passes": {
						complexity = 4;
						break;
					}
					case "5 Passes": {
						complexity = 5;
					}
				}
			}
		});
		comboBox.setEditable(true);
		comboBox.setAlignmentY(Component.TOP_ALIGNMENT);
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1 Pass", "2 Passes", "3 Passes", "4 Passes", "5 Passes"}));
		panel.add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setAlignmentY(Component.TOP_ALIGNMENT);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNewCheckBox.setVerticalAlignment(SwingConstants.TOP);
		panel.add(chckbxNewCheckBox);
	}

}
