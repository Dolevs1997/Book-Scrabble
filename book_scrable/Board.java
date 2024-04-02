package book_scrable;

import java.util.ArrayList;

import book_scrable.Tile.Bag;

public class Board {
	ArrayList<Word> words;
	// ArrayList<Word> Allwords;
	private static Board boardstatic = null;
	public Tile[][] board = null;
	public final int[][] scoreBonus = {
			{ 6, 0, 0, 2, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 6 }, // 0
			{ 0, 5, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 5, 0 }, // 1
			{ 0, 0, 5, 0, 0, 0, 2, 0, 2, 0, 0, 0, 5, 0, 0 }, // 2 //yellow = 5
			{ 2, 0, 0, 5, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 2 }, // 3 //red = 6
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0 }, // 4 //blue = 3
			{ 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0 }, // 5 //light blue = 2
			{ 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0 }, // 6
			{ 6, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 2, 0, 0, 6 }, // 7
			{ 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0 }, // 8
			{ 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0 }, // 9
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0 }, // 10
			{ 2, 0, 0, 5, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 2 }, // 11
			{ 0, 0, 5, 0, 0, 0, 2, 0, 2, 0, 0, 0, 5, 0, 0 }, // 12
			{ 0, 5, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 5, 0 }, // 13
			{ 6, 0, 0, 2, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 6 }, // 14

	};

	private Board() {
		board = new Tile[15][15];
	}

	// public int setScore(Word word) {

	// }

	public int getScore(Word word) {
		int doubleWord = 1;
		int tripleWord = 1;
		int score = 0;
		int indexCol;
		int indexRow;
		if (word.vertical) {
			for (indexRow = 0; indexRow < word.T.length; indexRow++) {
				if (word.T[indexRow] != null) {
					if (scoreBonus[word.row + indexRow][word.col] == 5) { // yellow
						doubleWord = 2;

						score += board[word.row + indexRow][word.col].score;
					}
					if (scoreBonus[word.row + indexRow][word.col] == 6) { // red
						tripleWord = 3;
						score += board[word.row + indexRow][word.col].score;
					}
					if (scoreBonus[word.row + indexRow][word.col] == 3) { // blue
						score += board[word.row + indexRow][word.col].score * 3;
					}
					if (scoreBonus[word.row + indexRow][word.col] == 2) { // light blue
						score += board[word.row + indexRow][word.col].score * 2;
					}
					if (scoreBonus[word.row + indexRow][word.col] == 0) // no bonus
						score += board[word.row + indexRow][word.col].score;
				} else {
					if (scoreBonus[word.row + indexRow][word.col] == 2) // light blue
						score += board[word.row + indexRow][word.col].score * 2;
					if (scoreBonus[word.row + indexRow][word.col] == 3) // blue
						score += board[word.row + indexRow][word.col].score * 3;
					else
						score += board[word.row + indexRow][word.col].score;
				}
			}
		}

		else {

			for (indexCol = 0; indexCol < word.T.length; indexCol++) {
				if (word.T[indexCol] != null) {
					if (scoreBonus[word.row][word.col + indexCol] == 5) { // yellow
						doubleWord = 2;
						score += board[word.row][word.col + indexCol].score;
					}
					if (scoreBonus[word.row][word.col + indexCol] == 6) { // red
						tripleWord = 3;
						score += board[word.row][word.col + indexCol].score;
					}
					if (scoreBonus[word.row][word.col + indexCol] == 3) { // blue
						score += board[word.row][word.col + indexCol].score * 3;
					}
					if (scoreBonus[word.row][word.col + indexCol] == 2) { // light blue
						score += board[word.row][word.col + indexCol].score * 2;
					}
					if (scoreBonus[word.row][word.col + indexCol] == 0)
						score += board[word.row][word.col + indexCol].score;
				} else {
					if (scoreBonus[word.row][word.col + indexCol] == 3) { // blue
						score += board[word.row][word.col + indexCol].score * 3;
					}
					if (scoreBonus[word.row][word.col + indexCol] == 2) { // light blue
						score += board[word.row][word.col + indexCol].score * 2;
					} else
						score += board[word.row][word.col + indexCol].score;
				}
			}
		}
		if (words.size() > 1) {
			if (words.contains(word) && !words.get(0).equals(word)) {
				if (word.vertical == false) {
					for (int cols = 0; cols < word.T.length; cols++) {
						if (scoreBonus[word.row][word.col + cols] == 5)
							doubleWord = 1;
						if (scoreBonus[word.row][word.col + cols] == 6)
							tripleWord = 1;
					}
				} else {
					for (int rows = 0; rows < word.T.length; rows++) {
						if (scoreBonus[word.row + rows][word.col] == 5)
							doubleWord = 1;
						if (scoreBonus[word.row + rows][word.col] == 6)
							tripleWord = 1;
					}
				}
			}
		}
		return score * doubleWord * tripleWord;

	}

	public static Board getBoard() {
		if (boardstatic == null) {
			boardstatic = new Board();

		}
		return boardstatic;

	}

	public Tile[][] getTiles() {
		Tile[][] boardCopy = (Tile[][]) board.clone();
		return boardCopy;
	}

	public boolean boardLegal(Word w0) {
		int indexRow = 0;
		int indexCol = 0;
		if (w0.T.length >= 15) // length words too long
		{
			return false;
		}
		if (w0.vertical == false) {

			indexCol = w0.col + w0.T.length;
		}
		if (w0.vertical == true) {

			indexRow = w0.row + w0.T.length;
		}
		if (indexCol >= 15 || indexRow >= 15) // word is out of bound
		{
			return false;
		}

		if (w0.col < 0 || w0.row < 0 || w0.col >= 15 || w0.row >= 15) // word starts out of bound
		{
			return false;
		}

		for (indexRow = w0.row; indexRow < 15; indexRow++) {
			for (indexCol = w0.col; indexCol < 15; indexCol++) {
				if (indexCol > 0 && indexCol < 14 && indexRow > 0 && indexRow < 14) {
					// checking if there is initialized word on board
					if (board[indexRow][indexCol + 1] != null || board[indexRow][indexCol - 1] != null
							|| board[indexRow - 1][indexCol] != null || board[indexRow + 1][indexCol] != null) {
						return true;
					}
				}
			}

		}

		for (indexCol = w0.col; indexCol < 15; indexCol++) {
			if (indexCol == 7 && w0.row == 7) {
				return true;
			}
		}
		for (indexRow = w0.row; indexRow < 15; indexRow++) {
			if (indexRow == 7 && w0.col == 7) {
				return true;
			}
		}

		return false;

	}

	public int tryPlaceWord(Word word) {
		int wordScore = 0;

		int rows, cols;
		if (boardLegal(word) && dictionary())// if word legal - add to the array of words on the board
		// and take from the bag the tiles
		{

			Bag bag = Tile.Bag.getBag();
			for (int i = 0; i < word.T.length; i++) {
				if (word.T[i] != null)
					bag.getTile(word.T[i].letter);
			}

			int index;
			// put tiles on board
			if (word.vertical == false) {
				for (cols = word.col, index = 0; cols < word.T.length + word.col; cols++, index++) {
					if (word.T[index] != null)
						board[word.row][cols] = new Tile(word.T[index]);
				}

				// wordScore = getScore(word);

				// return wordScore;
			}

			else if (word.vertical) {

				for (rows = word.row, index = 0; rows < word.T.length + word.row; rows++, index++) {
					if (word.T[index] != null)
						board[rows][word.col] = new Tile(word.T[index]);
				}
				// wordScore = getScore(word);
				// return wordScore;
			}
			// adding words on board to dynamic array
			getWords(word);

			for (int i = 0; i < words.size(); i++) {
				wordScore += getScore(words.get(i));
			}
		}
		return wordScore;
	}

	public ArrayList<Word> getWords(Word W) {
		int indexWord;
		Tile[] T = null;
		// if(Allwords == null){
		// Allwords = new ArrayList<Word>();
		// }
		words = new ArrayList<Word>();

		words.add(W);

		if (W.vertical == false) { // FALSE = horizontal
			for (indexWord = 0; indexWord < W.T.length; indexWord++) {
				if ((board[W.row - 1][W.col + indexWord] != null ||
						board[W.row + 1][W.col + indexWord] != null) && W.T[indexWord] != null)
					words.add(setVerticaWord(T, W, W.col + indexWord));
			}
		}

		else { // TRUE = vertical
			for (indexWord = 0; indexWord < W.T.length; indexWord++) {
				if ((board[W.row + indexWord][W.col + 1] != null ||
						board[W.row + indexWord][W.col - 1] != null) && W.T[indexWord] != null)
					words.add(setHorizontalWord(T, W, W.row + indexWord));
			}
		}
		return words;
	}

	Word setVerticaWord(Tile[] T, Word W, int indexWord) {
		int indexRow = W.row;
		Word newW = null;
		while (board[indexRow][indexWord] != null) {
			indexRow--;
		}
		indexRow++;
		int saveRow = indexRow;
		int index = 0;
		while ((board[saveRow + index][indexWord] != null)) {
			index++;
		}
		int size = index;
		T = new Tile[size];
		int indexTiles = 0;
		while (board[indexRow][indexWord] != null) {
			T[indexTiles] = new Tile(board[indexRow][indexWord]);
			indexRow++;
			indexTiles++;
		}
		newW = new Word(T, saveRow, indexWord, true);
		return newW;
	}

	Word setHorizontalWord(Tile[] T, Word W, int indexWord) {
		int indexCol = W.col;
		Word newW = null;
		while (board[indexCol][indexWord] != null) {
			indexCol--;
		}
		indexCol++;
		int saveCol = indexCol;
		int index = 0;
		while ((board[indexWord][saveCol + index] != null)) {
			index++;
		}
		int size = index;
		T = new Tile[size];
		int indexTiles = 0;
		while (board[indexWord][indexCol] != null) {
			T[indexTiles] = new Tile(board[indexWord][indexCol]);
			indexCol++;
			indexTiles++;
		}
		newW = new Word(T, saveCol, indexWord, false);
		return newW;
	}

	boolean dictionary() {

		return true;
	}
}