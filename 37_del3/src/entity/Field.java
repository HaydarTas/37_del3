package entity;

public class Field {
	private String name;
	private int points;

	public Field(String name, int point) {
		this.name = name;
		this.points = point;
	}

	@Override
	public String toString() {
		return "felt [name=" + name + ", point=" + points + "]";
	}
	
	public int getPoint() {
		return points;
	}
	
	public String getName() {
		return name;
	}

}

