/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */
import java.util.*;
import java.io.*;
public class BipRed {
    Kattio io;
    int x;
    int y;
    int e;
    int[] xNodes;
    int[] yNodes;
    ArrayList<Integer> xxNodes;
    ArrayList<Integer> yyNodes;

    void readBipartiteGraph() {
        // Läs antal hörn och kanter
        x = io.getInt();
        y = io.getInt();
        e = io.getInt();
        xNodes=new int[x+y];
        yNodes=new int[x+y];
        xxNodes=new ArrayList<>();
        yyNodes=new ArrayList<>();

        // Läs in kanterna
        for (int i = 0; i < e; ++i) {
            int a = io.getInt();
            int b = io.getInt();
            xNodes[i]=a;
            yNodes[i]=b;
        }
        for (int i=0; i<x; i++) {
            xxNodes.add(i+1);
        }
        for (int i =0; i<y;i++) {
            yyNodes.add(x+i+1);
        }
    }


    void writeFlowGraph() {
        int v = x+y+2;
        e = e+x+y;
        int s = x+y+1;
        int t = x+y+2;
        // Skriv ut antal hörn och kanter samt källa och sänka
        io.println(v);
        io.println(s + " " + t);
        io.println(e);
        int count=0;
        for (int i = 0; i < e; ++i) {
            //int a = 1, b = 2, c = 11;
            // Kant från a till b med kapacitet c
            int c=1;
            if (i<x) {
                int a=s;
                int b=xxNodes.get(i);
                io.println(a + " " + b + " " + c);
            } else if (i<=x+y) {
                int a = xNodes[count];
                int b = yNodes[count];
                io.println(a + " " + b + " " + c);
                count++;
            } else {
                int a = yyNodes.get(i-(x+y+1));
                int b = t;
                io.println(a + " " + b + " " + c);
            }
        }
        //System.out.println(io.getInt());
        // Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
        io.flush();

        // Debugutskrift
        //System.err.println("Skickade iväg flödesgrafen");
    }


    void readMaxFlowSolution() {
        // Läs in antal hörn, kanter, källa, sänka, och totalt flöde
        // (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
        // skickade iväg)
        int v = io.getInt();
        int s = io.getInt();
        int t = io.getInt();
        int totflow = io.getInt();
        int e = io.getInt();

        for (int i = 0; i < e; ++i) {
            // Flöde f från a till b
            int a = io.getInt();
            int b = io.getInt();
            int f = io.getInt();
        }
    }


    void writeBipMatchSolution() {
        int x = 17, y = 4711, maxMatch = 0;

        // Skriv ut antal hörn och storleken på matchningen
        io.println(x + " " + y);
        io.println(maxMatch);

        for (int i = 0; i < maxMatch; ++i) {
            int a = 5, b = 2323;
            // Kant mellan a och b ingår i vår matchningslösning
            io.println(a + " " + b);
        }

    }

    BipRed() {
        io = new Kattio(System.in, System.out);

        readBipartiteGraph();

        writeFlowGraph();

       // readMaxFlowSolution();

        //writeBipMatchSolution();

        // debugutskrift
        //System.err.println("Bipred avslutar\n");

        // Kom ihåg att stänga ner Kattio-klassen
        io.close();
    }

    public static void main(String args[]) {
        new BipRed();
    }
}
