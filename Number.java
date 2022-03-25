import java.util.Random;
public class Number {
	private Random rnd;
	private char color;
	private Coordinate position;
	private int value;
	private Stack path;
	private int yellowPath;
	public Number(char color, int xPosition, int yPosition, int value) {
		this.color = color;
		this.position=new Coordinate(xPosition,yPosition);
		this.value=value;
		rnd = new Random();
	}
	
	public Number(char color, int xPosition, int yPosition, int value,int capacity) {
		this.color = color;
		this.position=new Coordinate(xPosition,yPosition);
		this.value=value;
		this.path=new Stack(capacity);
	}
	
	public void changePosition() {
		Stack tempPath=new Stack(1000);
		while (!(path.isEmpty())) {
			tempPath.push(path.pop());
		}
		this.position=(Coordinate) tempPath.pop();
		while (!(tempPath.isEmpty())) {
			path.push(tempPath.pop());
		}
	}
	public boolean addPath(Coordinate coordinate) {
		if (path.isFull()) {
			return false;
		}else {
			path.push(coordinate);
			return true;
		}
	}
	
	public void removePath() {
		this.path=null;
		this.path=new Stack(1000);
	}
	
	public Stack getPath() {
		return path;
	}

	public void setPath(Stack path) {
		this.path = path;
	}

	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public Coordinate getPosition() {
		return position;
	}
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public int getYellowPath() {
		return yellowPath;
	}

	public void setYellowPath(int yellowPath) {
		this.yellowPath = yellowPath;
	}
	public void setPathYellow() {
		int rand = rnd.nextInt(4);
		switch (rand) {
			case 0: 
				yellowPath = 0;
				break;
			case 1: 
				yellowPath = 1;
				break;
			case 2: 
				yellowPath = 2;
				break;
			case 3: 
				yellowPath = 3;
				break;
		}
	}

}
