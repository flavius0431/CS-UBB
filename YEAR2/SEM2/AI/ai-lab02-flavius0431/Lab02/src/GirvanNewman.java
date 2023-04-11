import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GirvanNewman {

    //matricea cu sume ale matricei de comunitati pe linii
    public int[] a;
    //matricea de comunitati
    public int[][] e;

    //numarul de muchii ale comunitatii
    public int nr_muchii;
    //comunitatea- fiecare comunitate detine indexul nodului cel mai mic
    public int[] comunities;

    //initializam matricea de comunitati, suma comunitatilor pe fiecare linie si vectorul de comunitati
    public GirvanNewman (int[][] e) {
        this.e = new int[e.length][e.length];
        for (int k = 0; k < e.length; k++) {
            System.arraycopy(e[k], 0, this.e[k], 0, e.length);
        }
        this.a = new int[e.length];
        for (int i = 0; i < e.length; i++) {
            this.a[i] = sumai(e, i);
        }
        this.comunities = new int[e.length];
        for (int j = 0; j < e.length; j++) {
            comunities[j] = j;
        }
        for (int j = 0; j < e.length; j++) {

        }
    }

    /**
     * facem suma de pe fiecare linie
    * */
    public static int sumai(int[][] mat, int row) {
        int sum = 0;
        for (int i = 0; i <= row; i++) {
            sum = sum + mat[row][i];
        }
        return sum;
    }

    /**
     * formam comunitatile si stergem muchiile care
     * */
    public GirvanNewman girvanNewman(int i, int j) {

        //copiem matricea
        GirvanNewman gn = new GirvanNewman(e.clone());
        for (int k = 0; k < e.length; k++) {
            for (int z = 0; z < e.length; z++) {
                gn.e[i][j] = e[i][j];
            }

        }
        /*stabilim indicele cel mai mic(o conventie)
         */
        if (i < j) {
            int aux = i;
            i = j;
            j = aux;
        }
        //
        gn.e[i][j] += gn.e[j][j] + gn.e[i][j];
        gn.e[i][j] = 0;
        gn.e[j][j] = 0;


        for (int k = 0; k < e.length; k++) {
            if (k != i && k != j) {
                gn.e[k][i] += gn.e[k][j];
                gn.e[i][k] += gn.e[j][k];
                gn.e[j][k] = gn.e[k][j] = 0;
            }
        }

        gn.a = new int[a.length];
        for (int x = 0; x < e.length; x++) {
            for (int y = 0; y < e.length; y++) {
                gn.a[x] += gn.e[x][y];
            }
        }
        /*initializem fiecare nod i in comunitatea i
         */
        for (int s = 0; s < comunities.length; s++) {
            if (comunities[s] == j)
                comunities[s] = i;
        }
        nr_muchii = getNr_muchii(e);
        return gn;
    }

    /**functia care numara numarul de muchii
     */
    public int getNr_muchii(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i > j && mat[i][j] == 1)
                    nr_muchii++;
            }
        }
        return nr_muchii;
    }

    /**functie care afla fitnesul functiei, am adus la acelasi numitor pentru a nu lucra cu fractii
     */
    public int getQ() {
        int q = 0;
        for (int i = 0; i < e.length; i++) {
            q += nr_muchii * e[i][i] - a[i] * a[i];
        }
        return q;
    }


    /**functia care ne da numarul de comunitati
     */
    public int nr_comunitati() {
        return (int) Arrays.stream(comunities).distinct().count();
    }

    //clasa Muchie
    public class Muchie {
        public int u;
        public int v;

        public Muchie(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }


    /**functia ne returneaza numarul de muchii dintre comunitati
     */
     public List<Muchie> muchii_dintre_comunitati() {
        var list = new ArrayList<Muchie>();
        for (int i = 0; i < e.length; i++) {
            for (int j = i + 1; j < e.length; j++) {
                if (e[i][j] > 0)
                    list.add(new Muchie(i, j));
            }
        }
        return list;
    }

}