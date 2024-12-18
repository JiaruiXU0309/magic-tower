import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
public class Menu {
	Element rootElement;
	GameData gameData;
	Menu(GameData gameData) {
		this.gameData = gameData;
	}
	void enterMenu()
	{
		menuOperation(rootElement);
	}
	void loadMenu(String filePath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filePath));
			document.getDocumentElement().normalize();
			rootElement = document.getDocumentElement();
			removeTextNodes(rootElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void removeTextNodes(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				String textContent = child.getTextContent().trim();
				if (textContent.isEmpty()) {
					node.removeChild(child);
					i--;
				}
			} else if (child.getNodeType() == Node.ELEMENT_NODE) {
				removeTextNodes(child);
			}
		}
	}

	void displayMenu(Element element) {
		System.out.println("****" + element.getAttribute("title") + "****");
		NodeList childElements = element.getChildNodes();
		for (int i = 0; i < childElements.getLength(); i++) {
			Element childElement = (Element) childElements.item(i);
			System.out.println(Integer.toString(i + 1) + "." + childElement.getAttribute("title"));
		}
		System.out.println("0.Back to Upper Menu");
	}


	void menuOperation(Element element) {
		while (true) {
			displayMenu(element);
			Scanner keyboardInput = new Scanner(System.in);
			String input = keyboardInput.next();
			int inputNum;
			try {
				inputNum = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Wrong Input.");
				continue;
			}
			if (inputNum == 0)
				return;
			else {
				NodeList childElements = element.getChildNodes();
				if (inputNum > 0 && inputNum <= childElements.getLength()) {
					Element selectedElement = (Element) childElements.item(inputNum - 1);
					String isFunction = selectedElement.getAttribute("isFunction");
					if (isFunction.equals("No")) {
						menuOperation(selectedElement);
					} else {
						callMethod(isFunction);
					}
				} else {
					System.out.println("Wrong Input.");
				}
			}
		}
	}

	void callMethod(String functionName) {
		try {
			Class<?> clazz = this.getClass();
			Method method = clazz.getMethod(functionName);
			method.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void restartGame() {
		gameData.readMapFromFile("Map.in");
		System.out.println("Game Restarted!!");
	}

	public void quitGame() {
		System.exit(0);
	}

	public void saveGame() {
		try (FileOutputStream fileOut = new FileOutputStream("Game.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(gameData);
			System.out.println("Game Saved to Game.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGame() {
		try (FileInputStream fileIn = new FileInputStream("Game.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			gameData.copyFields((GameData) in.readObject());
			System.out.println("Gamed Loaded from Game.ser");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
