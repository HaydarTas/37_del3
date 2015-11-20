package entity;

public class Player {

	private String name;
	private Account account;
	private boolean bankrupt = false;

	public Player() {

		this.account = new Account();

		this.account.addToBalance(1000);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return this.account.getBalance();

	}

	public boolean addToBalance(int points) {
		return this.account.addToBalance(points);

	}

	public boolean isBankrupt() {
		return bankrupt ;
	}
	
	/**
	 * setBankrupt sets the bankrupt status to true. 
	 */
	public void setBankrupt(){
		bankrupt = true;
	}
}
