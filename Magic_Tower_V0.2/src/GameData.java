import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.lang.reflect.Field;

public class GameData implements Serializable {
	int L, H, W, currentLevel;
	int pX, pY, heroHealth, keyNum;
	int[][][] map;
	void readMapFromFile(String filePath) {
		currentLevel = 0;
		heroHealth = 105;
		keyNum = 0;
		pX = 3;
		pY = 3;
		try {
			Scanner in = new Scanner(new File(filePath));
			L = in.nextInt();
			H = in.nextInt();
			W = in.nextInt();
			map = new int[L][H][W];
			for (int i = 0; i < L; i++)
				for (int j = 0; j < H; j++)
					for (int k = 0; k < W; k++)
						map[i][j][k] = in.nextInt();
		} catch (IOException e) {
			System.out.println("Error with files:" + e.toString());
		}
	}

	void printMap() {
		char C[] = { 'W', '_', 'K', 'D', 'S', 'E', 'H' };
		for (int j = 0; j < H; j++) {
			for (int k = 0; k < W; k++) {
				if (map[currentLevel][j][k] < 0)
					System.out.print("M ");
				else if (map[currentLevel][j][k] > 10)
					System.out.print("P ");
				else
					System.out.print(C[map[currentLevel][j][k]] + " ");
			}
			System.out.print("\n\n");
		}
		System.out.print(
				"Health:" + Integer.toString(heroHealth) + "  KeyNum:" + Integer.toString(keyNum) + "  Menu:press 0\n");
	}

	void copyFields(Object source) {
		try {
			Class<?> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(source);
				field.set(this, value);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
