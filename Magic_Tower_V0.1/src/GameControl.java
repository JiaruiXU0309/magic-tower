package src;
import java.util.Scanner;

public class GameControl {
	GameData gameData;
	Menu menu;

	GameControl(GameData gameData, Menu menu) {
		this.gameData = gameData;
		this.menu = menu;
	}

	void gameStart() {
		Scanner keyboardInput = new Scanner(System.in);
		while (true) {
			String input = keyboardInput.next();
			if (input.length() != 1 || (input.charAt(0) != 'a' && input.charAt(0) != 's' && input.charAt(0) != 'd'
					&& input.charAt(0) != 'w' && input.charAt(0) != '0'&&input.charAt(0) != 'f')) {
				System.out.println("Wrong Input.");
				continue;
			}
			if (input.charAt(0) == '0')
				menu.enterMenu();
			else if (input.charAt(0) == 'f')
				activateSkill();
			else
				handleInput(input.charAt(0));
			gameData.printMap();
		}
	}

	void activateSkill() {
		if (gameData.skillCooldown == 0) {
			gameData.skillPower = 50; // 激活技能，削弱50%攻击力
			gameData.skillCooldown = 3; // 设置技能冷却时间为3个回合
			System.out.println("Skill activated! Monster attacks are now weakened by 50% for the next 3 turns.");
		} else {
			System.out.println("Skill is still on cooldown! " + gameData.skillCooldown + " turns left.");
		}
	}

	void handleInput(char inC) {
		int tX = 0, tY = 0;
		if (inC == 'a') {
			tX = gameData.pX;
			tY = gameData.pY - 1;
		}
		if (inC == 's') {
			tX = gameData.pX + 1;
			tY = gameData.pY;
		}
		if (inC == 'd') {
			tX = gameData.pX;
			tY = gameData.pY + 1;
		}
		if (inC == 'w') {
			tX = gameData.pX - 1;
			tY = gameData.pY;
		}
		if (gameData.map[gameData.currentLevel][tX][tY] == 2) {
			gameData.keyNum++;
			moveHero(tX, tY);
		} else if (gameData.map[gameData.currentLevel][tX][tY] == 3 && gameData.keyNum > 0) {
			gameData.keyNum--;
			moveHero(tX, tY);
		} else if (gameData.map[gameData.currentLevel][tX][tY] == 4) {
			gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;
			gameData.currentLevel++;
			for (int i = 0; i < gameData.H; i++)
				for (int j = 0; j < gameData.W; j++)
					if (gameData.map[gameData.currentLevel][i][j] == 6) {
						gameData.pX = i;
						gameData.pY = j;
					}
		} else if (gameData.map[gameData.currentLevel][tX][tY] == 5) {
			System.out.print("You Win!!");
			System.exit(0);
		} else if (gameData.map[gameData.currentLevel][tX][tY] > 10) {
			gameData.heroHealth += gameData.map[gameData.currentLevel][tX][tY];
			moveHero(tX, tY);
		} else if (gameData.map[gameData.currentLevel][tX][tY] == 1) {
			moveHero(tX, tY);
		} else if (gameData.map[gameData.currentLevel][tX][tY] < 0) {
			int monsterPower = gameData.map[gameData.currentLevel][tX][tY];
			int reducedMonsterPower = monsterPower * (100 - gameData.skillPower) / 100;

			if (reducedMonsterPower + gameData.heroHealth <= 0) {
				System.out.print("That monster has " + Integer.toString(-gameData.map[gameData.currentLevel][tX][tY])
						+ " power, You Lose!!");
				System.exit(0);
			} else {
				gameData.heroHealth += reducedMonsterPower;
				moveHero(tX, tY);
			}
		}
	}

	void moveHero(int tX, int tY) {
		gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;
		gameData.map[gameData.currentLevel][tX][tY] = 6;
		gameData.pX = tX;
		gameData.pY = tY;
	}
}
