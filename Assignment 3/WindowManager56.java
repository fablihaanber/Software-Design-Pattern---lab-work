/*
 * Author - Fabliha Anber
 * Roll -56
 * Software Design Pattern
 * Lab 03
 * 
 */


import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.awt.Color;
import java.awt.Font;

class ConfigFile {
	
	private File textFile = new File("configFile56");
	
	public File getFile() {
		
	//	/textFileParser/src/textFileParser/NewFile.xml
		
		
		return textFile;
	}
	
}

class XmlFile{
	
	private File xmlFile = new File("NewFile56.xml");
	
	public File getXMLFile() {
		

		return xmlFile;
	}
	
}

class XmlFileAdapter extends ConfigFile{
	
	private  XmlFile xmlFile;
	private FileWriter writer;
	private File textFile;
	
	XmlFileAdapter(XmlFile xmlFile){
		this.xmlFile = xmlFile;
	}
	
	
	
	private void convertXml() {
		
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		 try {
			 File XMLFile = xmlFile.getXMLFile();
			 DocumentBuilder builder = factory.newDocumentBuilder();
			 Document doc = builder.parse(XMLFile);
			 NodeList buttonList = doc.getElementsByTagName("Button");
			String buttons[] = new String[buttonList.getLength()];
			NodeList editBoxList = doc.getElementsByTagName("EditBox");
			String editBoxes[] = new String[editBoxList.getLength()];
			NodeList textBoxList = doc.getElementsByTagName("TextBox");
			String textBoxes[] = new String[textBoxList.getLength()];
			
			extractValues(buttonList,buttons,"Button");
			
			extractValues(editBoxList,editBoxes,"EditBox");
			extractValues(textBoxList, textBoxes, "TextBox");
		
			String newfileContent = "";
			for(String button:buttons) {
				newfileContent = newfileContent.concat(button + "\n");
			}
			for(String editBox:editBoxes) {
				newfileContent = newfileContent.concat(editBox + "\n");
			}
			for(String textBox:textBoxes) {
				newfileContent = newfileContent.concat(textBox + "\n");
			}
			
			textFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\textFileParser\\src\\textFileParser\\neww.txt");
			writer = new FileWriter(textFile);
			writer.write(newfileContent);
			writer.close();
			
			 
			
			
		 } catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		
	}
	 
	
	private void extractValues(NodeList elementList, String[] element, String string) {
		// TODO Auto-generated method stub
		for(int i=0;i<elementList.getLength();i++) {
			Node b = elementList.item(i);
			if(b.getNodeType()==Node.ELEMENT_NODE) {
				Element button = (Element) b;
				String value = button.getAttribute("value");
				String X = button.getAttribute("X");
				String Y = button.getAttribute("Y");
				element[i++] = string + ", " + value + ", X: " + X + ", Y: "+ Y;
				
			}
		}
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		convertXml();
		
		return textFile;
	}
} 


class ConfigManager {
	
	private static ConfigFile configFile;
	
	private static File file;
	private static Scanner scan;
	
	
	private static String[] buttonValue;
	private static int[] buttonX;
	private static int[] buttonY;
	
	private static String[] editBoxValue;
	private static int[] editBoxX;
	private static int[] editBoxY;
	
	private static String[] textBoxValue;
	private static int[] textBoxX;
	private static int[] textBoxY;
	
	private static ConfigManager config = new ConfigManager();
	private ConfigManager() {
		
	}
	
	public  void fileManagement() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		configFile = new ConfigFile();
		
		XmlFile xmlFile = new XmlFile();
		XmlFileAdapter xmlFileAdapter = new XmlFileAdapter(xmlFile);
		File xmlConvertedFile = xmlFileAdapter.getFile();
		
		file = configFile.getFile();
				
		int buttonCount = 0;
		int textBoxCount = 0;
		int editBoxCount = 0;
		
		scan = new Scanner(file);
		
		String fileContent = "";   // this file will store the values of text file and xml file
		while(hasMoreItem()) {
			String nextContent = nextItem();
			char[] chars = nextContent.toCharArray();
			if(chars[0]=='B') {
				buttonCount++;
			}
			if(chars[0]=='E') {
				editBoxCount++;
			}
			if(chars[0]=='T') {
				textBoxCount++;
			}
			fileContent = fileContent.concat(nextContent + "\n");
			
		}
		
		scan = new Scanner(xmlConvertedFile);
		
		while(hasMoreItem()) {
			String nextContent = nextItem();
			char[] chars = nextContent.toCharArray();
			if(chars[0]=='B') {
				buttonCount++;
			}
			if(chars[0]=='E') {
				editBoxCount++;
			}
			if(chars[0]=='T') {
				textBoxCount++;
			}
			fileContent = fileContent.concat(nextContent + "\n");
			
		}
		
		
		String[] fileString = fileContent.split("\n");
		Arrays.sort(fileString);
		buttonValue = new String[buttonCount];
		buttonX = new int[buttonCount];
		buttonY = new int[buttonCount];
		
		editBoxValue = new String[editBoxCount];
		editBoxX = new int[editBoxCount];
		editBoxY = new int[editBoxCount];
		
		textBoxValue = new String[textBoxCount];
		textBoxX = new int[textBoxCount];
		textBoxY = new int[textBoxCount];
		
		buttonCount = 0;
		editBoxCount = 0;
		textBoxCount = 0;
		for(String string:fileString) {
			System.out.println(string);
			char[] chars = string.toCharArray();
			if(chars[0]=='B') {
				String[] split = string.split(",");
				buttonValue[buttonCount] = split[1];
				String[] splitCoordinatesX = split[2].split(":");
				String strx = splitCoordinatesX[1].substring(1, splitCoordinatesX[1].length());
				
				//
				String[] splitCoordinatesY = split[3].split(":");
				String stry = splitCoordinatesY[1].substring(1, splitCoordinatesY[1].length());
				
				int x = Integer.parseInt(strx);
				
				int y = Integer.parseInt(stry);
				
				
				buttonX[buttonCount] = x;
				buttonY[buttonCount] =	y;	
				buttonCount++;
			}
			
			else if(chars[0]=='T') {
				String[] split = string.split(",");
				textBoxValue[textBoxCount] = split[1];
				String[] splitCoordinatesX = split[2].split(":");
				String strx = splitCoordinatesX[1].substring(1, splitCoordinatesX[1].length());
				
				//
				String[] splitCoordinatesY = split[3].split(":");
				String stry = splitCoordinatesY[1].substring(1, splitCoordinatesY[1].length());
				
				int x = Integer.parseInt(strx);
				
				int y = Integer.parseInt(stry);
				
				
				textBoxX[textBoxCount] = x;
				textBoxY[textBoxCount] =	y;	
				textBoxCount++;
			}
			
			else if(chars[0]=='E') {
				String[] split = string.split(",");
				editBoxValue[editBoxCount] = split[1];
				String[] splitCoordinatesX = split[2].split(":");
				String strx = splitCoordinatesX[1].substring(1, splitCoordinatesX[1].length());
				
				//
				String[] splitCoordinatesY = split[3].split(":");
				String stry = splitCoordinatesY[1].substring(1, splitCoordinatesY[1].length());
				
				int x = Integer.parseInt(strx);
				
				int y = Integer.parseInt(stry);
				
				
				editBoxX[editBoxCount] = x;
				editBoxY[editBoxCount] =	y;	
				editBoxCount++;
			}
			
			
		}
		
		
		 
	}


	

	public static boolean hasMoreItem() {
		
		return scan.hasNextLine();
	}


	public static String nextItem() {  //returns
		
		return scan.nextLine();
		
	}
	
	public static ConfigManager getConfigInstance() { //Returning the singleton class
		return config;
	}
	
	
	public String[] getButtonValue() {
		return buttonValue;
	} 
	public int[] getButtonXcoordinate() {
		// TODO Auto-generated method stub
		return buttonX;
	}
	
	public int[] getButtonYcoordinate() {
		// TODO Auto-generated method stub
		return buttonY;
	}
	
	public  String[] getEditBoxValue() {
		return editBoxValue;
	} 
	public int[] getEditBoxXcoordinate() {
		// TODO Auto-generated method stub
		return editBoxX;
	}
	public int[] getEditBoxYcoordinate() {
		// TODO Auto-generated method stub
		return editBoxY;
	}
	
	public  String[] getTextBoxValue() {
		return textBoxValue;
	} 
	public int[] getTextBoxXcoordinate() {
		// TODO Auto-generated method stub
		return textBoxX;
	}
	public int[] getTextBoxYcoordinate() {
		// TODO Auto-generated method stub
		return textBoxY;
	}
	
	

}

interface DesignFactory{
	 public TextBox createTextBox();
	 public EditBox createEditBox();
	 public Button createButton();
}

class SimpleFactory implements DesignFactory{

	@Override
	public TextBox createTextBox() {
		return new SimpleTextBox();
		// TODO Auto-generated method stub
		
	}

	@Override
	public EditBox createEditBox() {
		return new SimpleEditBox();
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button createButton() {
		return new SimpleButton();
		// TODO Auto-generated method stub
		
	}

	
	
}
class HighDetailedFactory implements DesignFactory{

	@Override
	public TextBox createTextBox() {
		
		return  new HighDetailedTextBox();
		// TODO Auto-generated method stub
		
	}

	@Override
	public EditBox createEditBox() {
		// TODO Auto-generated method stub
		return new HighDetailedEditBox();
		
	}

	@Override
	public Button createButton() {
		// TODO Auto-generated method stub
		return new HighDetailedButton();
		
	}

	
}
interface EditBox{
	JTextField editText(int x, int y, String value);

}
interface TextBox{
	JLabel showText(int x, int y, String value);
}
interface Button{
	JButton clickButton(int x,int y,String value);
}
class SimpleEditBox implements EditBox{
	
	private JTextField userText;
	@Override
	public JTextField editText(int x, int y, String value) {
		// TODO Auto-generated method stub
		userText = new JTextField(value);
		
		System.out.println("I am a simple edit text");
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		
		userText.setFont(new Font("Arial", Font.PLAIN, 20));
		userText.setBackground(new java.awt.Color(r,g,b));
		userText.setBounds(x, y, 180, 25);
		userText.setForeground(new java.awt.Color(r2,g2,b2));
		
		
		return userText;
	}
	
}
class HighDetailedEditBox implements EditBox{
	
	private JTextField userText;
	@Override
	public JTextField editText(int x, int y, String value) {
		// TODO Auto-generated method stub
		userText = new JTextField(value);
		
		System.out.println("I am a high detailed edit text");
		
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		int fontSize = random.nextInt(30)+5;
		userText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		userText.setBackground(new java.awt.Color(r,g,b));
		userText.setBounds(x, y, 180, 25);
		userText.setForeground(new java.awt.Color(r2,g2,b2));
		
		
		return userText;
		
	}
	
}
class SimpleTextBox implements TextBox{
	private JLabel textBox;
	@Override
	public JLabel showText(int x, int y, String value) {
		
		textBox = new JLabel(value);
		
		System.out.println("I am a simple text box");
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		
		textBox.setFont(new Font("Arial", Font.PLAIN, 20));
		textBox.setBackground(new java.awt.Color(r,g,b));
		textBox.setBounds(x, y, 400, 250);
		textBox.setForeground(new java.awt.Color(r2,g2,b2));
		
		
		return textBox;
	}

	
}
class HighDetailedTextBox implements TextBox{
	
	private JLabel textBox;
	@Override
	public JLabel showText(int x, int y, String value) {
		
		textBox = new JLabel(value);
		
		
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		int fontSize = random.nextInt(30)+5;
		textBox.setFont(new Font("Arial", Font.PLAIN, fontSize));
		textBox.setBackground(new java.awt.Color(r,g,b));
		textBox.setBounds(x, y, 400, 250);
		textBox.setForeground(new java.awt.Color(r2,g2,b2));
		
		
		System.out.println("I am a High detailed text box");
		return textBox;
	}

	
	
}
class SimpleButton implements Button{
	private  JButton button;
	
	@Override
	public JButton clickButton(int x,int y,String value) {
		// TODO Auto-generated method stub
		System.out.println("I am a simple detailed button");
		button = new JButton(value);
		
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.setBackground(new java.awt.Color(r,g,b));
		button.setBounds(x, y, 200, 100);
		button.setForeground(new java.awt.Color(r2,g2,b2));
		
		return button;
	}
	
	
	
	

	
	
}
class HighDetailedButton implements Button{
	
	private  JButton button;
	
	@Override
	public JButton clickButton(int x,int y,String value) {
		// TODO Auto-generated method stub
		System.out.println("I am a high detailed button");
		
		
		button = new JButton(value);
		
		
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		int r2 = random.nextInt(255);
		int g2 = random.nextInt(255);
		int b2 = random.nextInt(255);
		
		int fontSize = random.nextInt(30)+5;
		button.setFont(new Font("Arial", Font.PLAIN, fontSize));
		
		button.setBackground(new java.awt.Color(r,g,b));
		button.setBounds(x, y, 200, 100);
		button.setForeground(new java.awt.Color(r2,g2,b2));
		
		return button;
	}

	
	
}
class GUIApplication{
	private DesignFactory factory;
	private Button button;
	private EditBox editBox;
	private TextBox textBox;
	
	private int buttonX;
	private int buttonY;
	
	private int editBoxX;
	private int editBoxY;
	
	private int textBoxX;
	private int textBoxY;
	
	private String buttonValue;
	private String editBoxValue;
	private String textBoxValue;
			
	
	GUIApplication(DesignFactory factory){
		this.factory=factory;
	}
	
	public void createUI() {
		this.button = factory.createButton();
		this.editBox = factory.createEditBox();
		this.textBox = factory.createTextBox();
	}
	
	public void setButton(int buttonX,int buttonY, String buttonValue) {
		this.buttonX = buttonX;
		this.buttonY = buttonY;
		this.buttonValue = buttonValue;
	}
	public void setEditBox(int editBoxX,int editBoxY, String EditBoxValue) {
		this.editBoxX = editBoxX;
		this.editBoxY = editBoxY;
		this.editBoxValue = EditBoxValue;
		}
	public void setTextBox(int textBoxX,int textBoxY, String TextBoxValue) {
		this.textBoxX = textBoxX;
		this.textBoxY = textBoxY;
		this.textBoxValue = TextBoxValue;
	}
	
	
	public JButton clickButton() {
		
		  return button.clickButton(buttonX,buttonY,buttonValue);
	
		
	}
	public JLabel showText() {
		return textBox.showText(textBoxX,textBoxY,textBoxValue);
		
	}
	public JTextField editText() {
		return editBox.editText(editBoxX,editBoxY,editBoxValue);
		
	}
	
	
	
}
public class WindowManager56 {
	
	private static JFrame frame;
	private static JLabel label;
	private static JPanel panel;
	private static JButton button;
	private static JTextField editText;
	private static GUIApplication app;
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		System.out.println("Which design do you prefer?");
		System.out.println("1. Simple Design Pattern");
		System.out.println("2. Factory Design pattern");
		
		String inputString = new String();
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
		
		DesignFactory factory;
		
		inputString = Integer.toString(userInput);
		if(userInput==1) {
			factory = new SimpleFactory();
		}
		else if(userInput==2) {
			factory = new HighDetailedFactory();
		}
		else {
			throw new Exception("Design not found");
		}
		
		 app = new GUIApplication(factory);
		app.createUI();
		
		
		frame = new JFrame();
		panel = new JPanel();
		
		
		frame.setSize(700, 700);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GuiLa!");
		frame.setVisible(true);
		
		
		panel.setLayout(null);
		
		ConfigManager config = ConfigManager.getConfigInstance(); //Calling the Singleton class that contains all the data
		loadUI(config);	
	
		
	}
	
	public static void loadUI(ConfigManager config) {
		try {
			
			config.fileManagement();
			
			String[] buttonValue = config.getButtonValue();
			int[] buttonX = config.getButtonXcoordinate();
			int[] buttonY = config.getButtonYcoordinate();
			
			String[] editBoxValue= config.getEditBoxValue();
		    int[] editBoxX= config.getEditBoxXcoordinate();
			int[] editBoxY = config.getEditBoxYcoordinate();
			
			String[] textBoxValue= config.getTextBoxValue();
			int[] textBoxX= config.getTextBoxXcoordinate();
			int[] textBoxY = config.getTextBoxYcoordinate();
			
			for(int i=0; i< buttonValue.length; i++) {
				app.setButton(buttonX[i],buttonY[i], buttonValue[i]);
				button = app.clickButton();
				panel.add(button);
			}
			
			
			for(int i=0; i< textBoxValue.length; i++) {
				
				app.setTextBox(textBoxX[i],textBoxY[i], textBoxValue[i]);
				label = app.showText();
				panel.add(label);
			}
			
			for(int i=0; i< editBoxValue.length; i++) {
				
				app.setEditBox(editBoxX[i],editBoxY[i], editBoxValue[i]);
				editText = app.editText();
				panel.add(editText);
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
