package hw8;

public class Video {
	
	private String SKU;
	private String title;
	private int price;
	private int quantity;
	
	public Video (String SKU, String title, int price, int quantity) {
		this.SKU = SKU;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String toString () {
		return "SKU: " + SKU + "\n" + "Title: " + title + "\n" + "Price: $" + price + "\n" + "Quantity: " + quantity + "\n";
	}
	
	// Setters and Getters
	
	/**
	 * @return the sKU
	 */
	public String getSKU() {
		return SKU;
	}
	/**
	 * @param sKU the sKU to set
	 */
	public void setSKU(String SKU) {
		this.SKU = SKU;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
