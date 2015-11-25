import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;


public class GUI extends JFrame {

	/**
	 * 
	 */
	
	public static BufferedImage getScaledPic(BufferedImage pic, int width, int height)
	{
		int picWidth = pic.getWidth();
		int picHeight = pic.getHeight();
		
		double scaleX=(double)width/picWidth;
		double scaleY=(double)height/picHeight;
		
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		AffineTransformOp ScaleOp=new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
		
		return ScaleOp.filter(pic, new BufferedImage(width, height, pic.getType()));
	}

	public GUI()
	{
		super("Lab5");
		setSize(500,500);
		setLocation(100,100);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane jtp=new JTabbedPane();
		
		JButton btn1=new JButton("Button 1");
		JButton btn2=new JButton("Button 2");
		btn2.setBackground(Color.RED);
		btn2.setOpaque(true);
		JPanel bar=new JPanel();
		bar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bar.add(btn1);
		bar.add(btn2);
		
		JPanel bottom=new JPanel(new BorderLayout());
		Border bevelBorder=BorderFactory.createRaisedBevelBorder();
		JLabel centralLabel=new JLabel("This is the south quadrant");
		JLabel leftLabel=new JLabel("West");
		JLabel rightLabel=new JLabel("East");
		centralLabel.setBorder(bevelBorder);
		bottom.add(centralLabel, BorderLayout.CENTER);
		bottom.add(leftLabel, BorderLayout.WEST);
		bottom.add(rightLabel, BorderLayout.EAST);
		bottom.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JPanel firstLayout=new JPanel(new BorderLayout());
		firstLayout.add(bar, BorderLayout.NORTH);
		firstLayout.add(bottom, BorderLayout.SOUTH);
		

		BufferedImage originalPic = null;
		
		try {
			originalPic=ImageIO.read(new File("./google_chrome.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage picPage=getScaledPic(originalPic, 500,500);

		JLabel picLabel=new JLabel(new ImageIcon(picPage));
		JPanel picLayout=new JPanel();
		picLayout.add(picLabel);
		
		jtp.add("First", firstLayout);
		jtp.add("Pic", picLayout);
		add(jtp);
		setVisible(true);
	}
	
	public static void main(String[]args)
	{
		GUI Lab5=new GUI();
	}
}
