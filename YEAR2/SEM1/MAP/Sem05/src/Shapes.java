import java.util.ArrayList;
import java.util.List;

public class Shapes {
    public static <E> void printArie(List<E> l, Area<E> f) {
        l.forEach(x->System.out.println(f.computeArea(x)));
    }
    public static void main(String[] args) {
        Area<Cerc> cercArea = (Cerc x)->(float)(Math.PI)*x.getRaza()*x.getRaza();
        Cerc cerc = new Cerc(1);
        Cerc cerc1 = new Cerc(3);
        float suprafataCerc= cercArea.computeArea(cerc);
        System.out.println(suprafataCerc);

        Area<Patrat> patratArea = x-> x.getLatura()*x.getLatura();
        List<Cerc> listCerc=new ArrayList<Cerc>();
        listCerc.add(cerc);
        listCerc.add(cerc1);
        printArie(listCerc,cercArea);
    }
}

class Cerc {
    private float raza;

    public float getRaza() {
        return raza;
    }

    public void setRaza(float raza) {
        this.raza = raza;
    }

    public Cerc(float raza) {
        this.raza = raza;
    }
}

class Patrat{
    private float latura;

    public float getLatura() {
        return latura;
    }

    public void setLatura(float latura) {
        this.latura = latura;
    }

    public Patrat(float latura) {
        this.latura = latura;
    }
}