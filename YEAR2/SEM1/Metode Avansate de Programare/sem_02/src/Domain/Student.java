package Domain;

import java.util.Objects;

public class Student extends Entity<Long>{
    private String nume;
    private Float media;

    public Student(String nume, Float media) {
        this.nume = nume;
        this.media = media;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Float getMedia() {
        return media;
    }

    public void setMedia(Float media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return nume + " " + media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getNume().equals(student.getNume()) && Objects.equals(getMedia(), student.getMedia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getMedia());
    }

    public int getMediaRotunjita(){
        return Math.round(getMedia());
    }
}
