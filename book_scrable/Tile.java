package book_scrable;

import java.util.Objects;

public class Tile {
	public final char letter;
	public final int score;

	private Tile(char letter, int score) {
		super();
		this.letter = letter;
		this.score = score;
	}

	public Tile(Tile tile) {
		this.letter = tile.letter;
		this.score = tile.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(letter, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		return letter == other.letter && score == other.score;
	}

	// CLASS BAG
	public static class Bag {
		private static Bag bag = null;
		int[] tilequantity; // every letter holds size
		Tile[] arraytiles; // tiles of 26 letters A,B,C
		// methods

		private Bag() {
			tilequantity = new int[26];
			arraytiles = new Tile[26];
			setTileQuantity(tilequantity);
			setArrayTiles(arraytiles);
		}

		public void setTileQuantity(int[] quantites) {
			quantites[0] = 9;
			quantites[1] = 2;
			quantites[2] = 2;
			quantites[3] = 4;
			quantites[4] = 12;
			quantites[5] = 2;
			quantites[6] = 3;
			quantites[7] = 2;
			quantites[8] = 9;
			quantites[9] = 1;
			quantites[10] = 1;
			quantites[11] = 4;
			quantites[12] = 2;
			quantites[13] = 6;
			quantites[14] = 8;
			quantites[15] = 2;
			quantites[16] = 1;
			quantites[17] = 6;
			quantites[18] = 4;
			quantites[19] = 6;
			quantites[20] = 4;
			quantites[21] = 2;
			quantites[22] = 2;
			quantites[23] = 1;
			quantites[24] = 2;
			quantites[25] = 1;

		}

		public void setArrayTiles(Tile[] t) {
			arraytiles[0] = new Tile('A', 1);
			arraytiles[1] = new Tile('B', 3);
			arraytiles[2] = new Tile('C', 3);
			arraytiles[3] = new Tile('D', 2);
			arraytiles[4] = new Tile('E', 1);
			arraytiles[5] = new Tile('F', 4);
			arraytiles[6] = new Tile('G', 2);
			arraytiles[7] = new Tile('H', 4);
			arraytiles[8] = new Tile('I', 1);
			arraytiles[9] = new Tile('J', 8);
			arraytiles[10] = new Tile('K', 5);
			arraytiles[11] = new Tile('L', 1);
			arraytiles[12] = new Tile('M', 3);
			arraytiles[13] = new Tile('N', 1);
			arraytiles[14] = new Tile('O', 1);
			arraytiles[15] = new Tile('P', 3);
			arraytiles[16] = new Tile('Q', 10);
			arraytiles[17] = new Tile('R', 1);
			arraytiles[18] = new Tile('S', 1);
			arraytiles[19] = new Tile('T', 1);
			arraytiles[20] = new Tile('U', 1);
			arraytiles[21] = new Tile('V', 4);
			arraytiles[22] = new Tile('W', 4);
			arraytiles[23] = new Tile('X', 8);
			arraytiles[24] = new Tile('Y', 4);
			arraytiles[25] = new Tile('Z', 10);

		}

		public Tile getRand() // random tile
		{
			for (int indextile = 0; indextile < tilequantity.length; indextile++) {
				if (tilequantity[indextile] - 1 > 0) {
					tilequantity[indextile]--;
					return arraytiles[indextile];
				}
			}
			return null;

		}

		public Tile getTile(char letter) // take tile from bag
		{

			for (int index = 0; index < arraytiles.length; index++) {
				if (arraytiles[index].letter == letter) {
					tilequantity[index]--;
					return arraytiles[index];
				}
			}
			return null;
		}

		public static Bag getBag() // create bag object
		{

			if (bag == null) {
				bag = new Bag();

			}
			return bag;
		}

		public int[] getQuantities() {
			int[] quantityTilesCopy = (int[]) tilequantity.clone();
			return quantityTilesCopy;
		}

		public void put(Tile tile) {
			if (size() < 98) {
				for (int indextile = 0; indextile < arraytiles.length; indextile++) {
					if (arraytiles[indextile].letter == tile.letter) {
						tilequantity[indextile]++;
						break;
					}
				}
			}

		}

		public int size() {
			int sum = 0;
			for (int index = 0; index < tilequantity.length; index++) {
				sum += tilequantity[index];
			}
			return sum;
		}

	}

}
