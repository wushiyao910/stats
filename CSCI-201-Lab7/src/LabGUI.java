import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class LabGUI extends JFrame{
	
	private JButton myButton;
	private JPanel myPanel;
	
	public LabGUI()
	{
		super("LabGUI");
		setSize(500,500);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myButton=new JButton("Click Once");
		myButton.setSize(59, 45);
		
		myPanel=new SHIPanel();
		
		myButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				myButton.setVisible(false);
				myPanel.remove(myButton);
				myButton=new SHIButton();
				myButton.setSize(59, 45);
				myButton.setText("Don't click me");
				myButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane jop=new JOptionPane();
						
						int selection=JOptionPane.showConfirmDialog(LabGUI.this, "WHY DID YOU CLICK AGAIN?", "Again", JOptionPane.OK_OPTION);
						switch(selection)
						{
						case JOptionPane.OK_OPTION:
							System.exit(0);
						}
					}

				});
				myPanel.add(myButton);
			}
			
		});
		myPanel.add(myButton);
		add(myPanel);
		
		setVisible(true);
	}
	
	public static void main(String[]args)
	{
		new LabGUI();
	}

}

class SHIPanel extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Polygon poly = new Polygon();
		poly.addPoint(240, 40);
		poly.addPoint(280, 40);
		poly.addPoint(300, 80);
		poly.addPoint(300, 108);
		poly.addPoint(280, 148);
		poly.addPoint(240, 148);
		poly.addPoint(220, 108);
		poly.addPoint(220, 80);
		g.drawPolygon(poly);
		g.setColor(Color.BLUE);
		g.fillPolygon(poly);
	}
}

class SHIButton extends JButton
{
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);				
		g.setColor(Color.RED);
		g.drawLine(0, 0, getWidth(), getHeight());
		g.drawLine(0, getHeight(), getWidth(), 0);
	}
}
