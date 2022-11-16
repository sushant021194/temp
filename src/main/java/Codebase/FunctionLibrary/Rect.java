package Codebase.FunctionLibrary;

public class Rect {
	public int x; // Array Index
	public int y; // Array Index
	public int w; // Number of hops along the Horizontal
	public int h; // Number of hops along the Vertical

	@Override
	public boolean equals(Object obj) {
		Rect rect = (Rect) obj;
		if(rect.x == this.x && rect.y == this.y && rect.w == this.w && rect.h == this.h) {
			return true;
		}
		return false;
	}
}
