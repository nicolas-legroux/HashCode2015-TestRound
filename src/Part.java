
public class Part {
	
	int left;
	int right;
	int top;
	int bottom;
	
	Part(int i, int j, int width, int height) {
		left = j;
		right = j + width - 1;
		top = i;
		bottom = i + height - 1;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        Part key = (Part) o;
        return left == key.left && right == key.right && top == key.top && bottom == key.bottom;
    }
	
	boolean contains(int x, int y) {
		return x >= left && x <= right && y >= top && y <= bottom;
	}
	
	void print() {
		System.out.print("{" + left + ", " + right + ", " + top + ", " + bottom + "}");
	}

}
