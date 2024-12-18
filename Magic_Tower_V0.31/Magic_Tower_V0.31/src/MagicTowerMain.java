import java.sql.SQLException;

public class MagicTowerMain {
	static GameData gameData;
	static GameControl gameControl;
	static Menu menu;
	static GUI gui;
	static Login login;
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		login=new Login();
		gameData= new GameData();
		gameData.readMapFromFile("Map.in"); 
		gameData.printMap();
		gui=new GUI(gameData);
		menu=new Menu(gameData);
		menu.loadMenu("Menu.XML");
		//menu.displayMenu(menu.rootElement);
		gameControl=new GameControl(gameData,menu,gui);
		gameControl.gameStart();
	}
}