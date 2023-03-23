package main.java.login.dataClass;

public class Account {
    private String id;
    private String psw;
    public Account(String id, String psw) {
        this.id = id;
        this.psw = psw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPsw() {
        return psw;
    }

}
