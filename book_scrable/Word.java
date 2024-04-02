package book_scrable;

import java.util.Arrays;
import java.util.Objects;

public class Word {

	Tile[] T;
	int row, col;
	boolean vertical;

	public Word(Tile[] t, int row, int col, boolean vertical) {
		super();
		T = t;
		this.row = row;
		this.col = col;
		this.vertical = vertical;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(T);
		result = prime * result + Objects.hash(col, row, vertical);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Arrays.equals(T, other.T) && col == other.col && row == other.row && vertical == other.vertical;
	}

	/**
	 * @return the t
	 */
	public Tile[] getT() {
		return T;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Tile[] t) {
		T = t;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the vertical
	 */
	public boolean isVertical() {
		return vertical;
	}

	/**
	 * @param vertical the vertical to set
	 */
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

}
