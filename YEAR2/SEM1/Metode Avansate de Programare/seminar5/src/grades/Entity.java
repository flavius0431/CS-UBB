package grades;

public class Entity<ID> {
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    private ID id;
}
