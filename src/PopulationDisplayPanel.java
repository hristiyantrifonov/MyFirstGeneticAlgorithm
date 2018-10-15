import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopulationDisplayPanel extends JPanel{

	String target;
	List<DNA> dnaList;
	int generationsNum;
	JLabel bestMatchLabel;

	final int X_COORDINATE = 950;
	int y_coordinate = 20;

	public PopulationDisplayPanel() {
		super();
		setOpaque(false);
		setLayout(new BorderLayout());
		this.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void paint(Graphics g) {


	}
}
