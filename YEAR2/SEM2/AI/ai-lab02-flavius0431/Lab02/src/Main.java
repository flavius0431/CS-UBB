import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {

    public static int n;
    GirvanNewman gn;
    public static int[][] mat=new int[1000][1000] ;
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //System.out.println("Hello Lab AI!");

        int[][] mat=citesteGraful("Lab02/src/fotball");
        int[][] matf=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matf[i][j]=mat[i+1][j+1];
            }
        }
        GirvanNewman gn = get_communities(matf);
        //System.out.println(gn.nr_comunitati());

    }
    public static void afisareMatrix(){
        for (int i=0;i<n;i++ ) {
            for (int j=0;j<n;j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }
    /***
     * 1.Citirea datelor
     * */
    public static int[][] citesteGraful(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linie;
            int m = 0;

            while((linie=br.readLine())!=null){
                List<String> atrr = Arrays.asList(linie.split(" "));
                int i =parseInt(atrr.get(0));
                int j =parseInt(atrr.get(1));
                //formam matricea de comunitati care initial este reprezentata ca fiecare nod sa fie in comunitatea lui
                mat[i][j]=mat[j][i]=1;
                if(i>m){
                    m=i;
                }
                if( j > m ) {
                    m = j;
                }
            }
            n=m;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mat;
    }

    /***
     * funcctia ne da comunitatile dintr=o matrice de adiacenta
     *
     */


   public static GirvanNewman get_communities(int[][] mat) {
        GirvanNewman gn = new GirvanNewman(mat);
        var edges_list = gn.muchii_dintre_comunitati() ;
        GirvanNewman chosen_gn = null;

        while(edges_list.size()> 0) {
            for(var e:edges_list) {
                var candidat_gn = gn.girvanNewman(gn.comunities[e.u], gn.comunities[e.v]);
                if(chosen_gn ==null){
                    chosen_gn=candidat_gn;
                }else if(candidat_gn.getQ() >= chosen_gn.getQ()) {
                    chosen_gn = candidat_gn;
                    System.out.println(candidat_gn.getQ());
                }
            }
            edges_list.remove(edges_list.size()-1);
            gn = chosen_gn;

        }

        return gn;
    }

}
