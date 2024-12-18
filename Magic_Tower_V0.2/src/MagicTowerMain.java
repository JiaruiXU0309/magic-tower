public class MagicTowerMain {
	static GameData gameData;
	static GameControl gameControl;
	static Menu menu;
	static GUI gui;
	public static void main(String[] args) {
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