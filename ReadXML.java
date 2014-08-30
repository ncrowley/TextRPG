import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXML {
	 
	  public static void main(String argv[]) {
	 
	    try {
	 
		File fXmlFile = new File("C:\\Users\\Nick\\OneDrive\\Documents\\Programming\\XML\\map.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
		NodeList nList = doc.getElementsByTagName("room");
		
		System.out.println("----------------------------");
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
			
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				
				System.out.println("Room ID: " + eElement.getAttribute("id"));
				System.out.println("Room Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
				for(int i = 0;i < eElement.getElementsByTagName("exit").getLength();i++) {
					System.out.println("Exit " + (i + 1) + ": " + eElement.getElementsByTagName("exit").item(i).getTextContent());
				}
				System.out.println("Enemy: " + eElement.getElementsByTagName("enemies").item(0).getTextContent());
				System.out.println("Items: " + eElement.getElementsByTagName("items").item(0).getTextContent());
				
	 
			}
		}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	  }
	 
	}
