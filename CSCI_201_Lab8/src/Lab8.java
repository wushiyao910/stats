import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Lab8 {
	
	public static void main(String[]args)
	{
		String ticSymbol;
		System.out.print("Enter the ticker symbol: ");
		Scanner sc=new Scanner (System.in);
		ticSymbol=sc.nextLine();
		
		String address="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"
				+ ticSymbol
				+ "%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		
		URL url=null;
		try {
			url=new URL(address);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db=dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc = null;
		try {
			doc=db.parse(url.openStream());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();
		Node nList=doc.getElementsByTagName("results").item(0);
		NodeList list=(NodeList) nList.getFirstChild();
		Element e=(Element)list;
		System.out.print(e.getElementsByTagName("Name").item(0).getTextContent());
		System.out.print("has a year low of "+e.getElementsByTagName("YearLow").item(0).getTextContent());
		System.out.print(" and a year high of "+e.getElementsByTagName("YearHigh").item(0).getTextContent());
		System.out.print(". It's current market captalization is "+e.getElementsByTagName("MarketCapitalization").item(0).getTextContent());
	}
}
