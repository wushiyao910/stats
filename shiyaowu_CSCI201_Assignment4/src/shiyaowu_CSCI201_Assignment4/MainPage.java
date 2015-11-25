package shiyaowu_CSCI201_Assignment4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainPage extends JFrame {
	
	JMenuBar jmb;
	JMenuItem openFile;
	File selectedFile;
	Tile [][]tiles;
	Document doc;
	
	
	public MainPage()
	{
		super("Roadway Simulator");
		setSize(800,600);
		setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jmb=new JMenuBar();
		openFile=new JMenuItem("Open file");
		jmb.add(openFile);
		setJMenuBar(jmb);
		
		openFile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.addChoosableFileFilter(new FileNameExtensionFilter(".xml", "xml"));
				int returnVal=jfc.showOpenDialog(getParent());
				if (returnVal==JFileChooser.APPROVE_OPTION)
				{
					selectedFile=jfc.getSelectedFile();
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db=null;
					try {
						db=dbf.newDocumentBuilder();
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						doc=db.parse(selectedFile);
					} catch (SAXException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					doc.getDocumentElement().normalize();
					tiles=new Tile[9][9];
					Node gridNode=doc.getElementsByTagName("grid").item(0);
					NodeList rowList=(NodeList)gridNode.getChildNodes();
					for (int i=0; i<rowList.getLength(); i++)
					{
						Node rowNode=rowList.item(i);
						if (rowNode.getNodeType()==Node.ELEMENT_NODE)
						{
							int rowNum = 0;
							Node rowAttribute=rowNode.getAttributes().item(0);
							String rowMark=rowAttribute.getNodeValue();
							if (rowMark.equals("A"))
							{
								rowNum=0;
							}
							else if (rowMark.equals("B"))
							{
								rowNum=1;
							}
							else if (rowMark.equals("C"))
							{
								rowNum=2;
							}
							else if (rowMark.equals("D"))
							{
								rowNum=3;
							}
							else if (rowMark.equals("E"))
							{
								rowNum=4;
							}
							else if (rowMark.equals("F"))
							{
								rowNum=5;
							}
							else if (rowMark.equals("G"))
							{
								rowNum=6;
							}
							else if (rowMark.equals("H"))
							{
								rowNum=7;
							}
							else if (rowMark.equals("I"))
							{
								rowNum=8;
							}
							
							NodeList tileList=(NodeList)rowNode.getChildNodes();
							for (int j=0; j<tileList.getLength(); j++)
							{
								Node tileNode=tileList.item(j);
								if (tileNode.getNodeType()==Node.ELEMENT_NODE)
								{
									int columnNum;
									String tileType;
									String tileDeg;
									
									NamedNodeMap attributeList=tileNode.getAttributes();
									
										columnNum=Integer.parseInt(tileNode.getAttributes().item(0).getNodeValue())-1;
										tileType=tileNode.getAttributes().item(1).getNodeValue().toString();
										tileDeg=tileNode.getAttributes().item(2).getNodeValue().toString();
									
									tiles[rowNum][columnNum]=new Tile(tileType, tileDeg);
								}
							}
						}
					}
					
				}
			}
			
		});
		
		
		//Part 3
		
		DrawPanel drawPage=new DrawPanel();
		this.add(drawPage,BorderLayout.CENTER);
		JPanel tabelPanel=new JPanel();
		String[]columnNames={"Car#", "X", "Y"};
		Object[][]data={{"","",""}};
		JTable table=new JTable(data, columnNames);
		Dimension d=new Dimension(200,600);
		JScrollPane jsp=new JScrollPane(table);
		jsp.setPreferredSize(d);
		tabelPanel.add(jsp);
		this.add(tabelPanel, BorderLayout.EAST);
		
		drawPage.setBackground(Color.WHITE);
		
		//Part4		
		Dimension frameSize=new Dimension(661,493);
		this.setMinimumSize(frameSize);;
		
		
		//Part5
		
		
		
		setVisible(true);
		
	}
	
	public static void main(String [] main)
	{
		new MainPage();
	}

}

class DrawPanel extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i=0; i<10; i++)
		{
			int extraWidth=(this.getWidth()-450)/2;
			int extraHeight=(this.getHeight()-450)/2;
			g.drawLine(i*50+extraWidth, 0+extraHeight, i*50+extraWidth, 450+extraHeight);
			g.drawLine(0+extraWidth, i*50+extraHeight, 450+extraWidth, i*50+extraHeight);
		}
	}
}
