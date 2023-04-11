package domain;

public class OficiuPersoana extends Entity<Long> {

    private String user;

    private String password;

    private String oficiu;

    public OficiuPersoana(String user, String password, String oficiu) {
        this.user = user;
        this.password = password;
        this.oficiu = oficiu;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOficiu() {
        return oficiu;
    }

    public void setOficiu(String oficiu) {
        this.oficiu = oficiu;
    }
}
