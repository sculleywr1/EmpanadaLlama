package models;

public class Fridge {
	private int fridgeId;
	private String item1;
	private String item2;
	private String item3;
	
	public Fridge() {
		// TODO Auto-generated constructor stub
	}


	public Fridge(String item1, String item2, String item3) {
		super();
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}


	public Fridge(int fridgeId, String item1, String item2, String item3) {
		super();
		this.fridgeId = fridgeId;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}


	public int getFridgeId() {
		return fridgeId;
	}

	public void setFridgeId(int fridgeId) {
		this.fridgeId = fridgeId;
	}


	public String getItem1() {
		return item1;
	}


	public void setItem1(String item1) {
		this.item1 = item1;
	}


	public String getItem2() {
		return item2;
	}


	public void setItem2(String item2) {
		this.item2 = item2;
	}


	public String getItem3() {
		return item3;
	}


	public void setItem3(String item3) {
		this.item3 = item3;
	}


	@Override
	public String toString() {
		return "Fridge [fridgeId=" + fridgeId + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + "]";
	}

	
}
