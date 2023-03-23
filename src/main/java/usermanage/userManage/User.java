package main.java.usermanage.userManage;

public class User {
	private String id;
	private String name;
	private String sex;
	private int entryyear;
	private String password;
	
	public User(String id,String name, String sex, int entryyear,String password) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.entryyear = entryyear;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setEntryyear(int entryyear) {
		this.entryyear = entryyear;
	}

	public int getEntryyear() {
		return entryyear;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
