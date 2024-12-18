package src;
public class MagicTowerMain {
	static GameData gameData;
	static GameControl gameControl;
	static Menu menu;
	public static void main(String[] args) {
		gameData= new GameData();
		gameData.readMapFromFile("Map.in"); 
		gameData.printMap();
		menu=new Menu(gameData);
		menu.loadMenu("Menu.XML");
		//menu.displayMenu(menu.rootElement);
		gameControl=new GameControl(gameData,menu);
		gameControl.gameStart();
	}
}