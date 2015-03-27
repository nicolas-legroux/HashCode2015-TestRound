
public class Part {
	
	int left;
	int right;
	int top;
	int bottom;
	
	boolean contains(int x, int y) {
		return x >= left && x <= right && y >= top && y <= bottom;
	}
	
	void print() {
		System.out.print("{" + left + ", " + right + ", " + top + ", " + bottom + "}");
	}

}
