package main.java.reader.bean;

public class BForm {
  String jy_id,jy_name,b_isbn,b_name;
  String jy_date,gh_date;
public String getJy_id() {
	return jy_id;
}
public void setJy_id(String jy_id) {
	this.jy_id = jy_id;
}
public String getJy_name() {
	return jy_name;
}
public void setJy_name(String jy_name) {
	this.jy_name = jy_name;
}
public String getB_isbn() {
	return b_isbn;
}
public void setB_isbn(String b_isbn) {
	this.b_isbn = b_isbn;
}
public String getB_name() {
	return b_name;
}
public void setB_name(String b_name) {
	this.b_name = b_name;
}
public String getJy_date() {
	return jy_date;
}
public void setJy_date(String jy_date) {
	this.jy_date = jy_date;
}
public String getGh_date() {
	return gh_date;
}
public void setGh_date(String string) {
	this.gh_date = string;
}
@Override
public String toString() {
	return "BForm [jy_id=" + jy_id + ", jy_name=" + jy_name + ", b_isbn=" + b_isbn + ", b_name=" + b_name + ", jy_date="
			+ jy_date + ", gh_date=" + gh_date + "]";
}
public BForm() {
	super();
	// TODO Auto-generated constructor stub
}
public BForm(String jy_id, String jy_name, String b_isbn, String b_name, String jy_date, String gh_date) {
	super();
	this.jy_id = jy_id;
	this.jy_name = jy_name;
	this.b_isbn = b_isbn;
	this.b_name = b_name;
	this.jy_date = jy_date;
	this.gh_date = gh_date;
}
  
  
}
