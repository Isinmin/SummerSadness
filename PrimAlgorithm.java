
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PrimAlgorithm {
    private final static int MAX_INT = 1000;
    private GraphCreation G;
    private int startVertex;
    private int quantityVertices;
    private int[] parent;
    private boolean[] intree;
    private int[] distance;

    private GraphCreation.Edge p;
    private int v;




    public PrimAlgorithm() {
        G = new GraphCreation();
        startVertex = 0;

        intree = new boolean[G.edges.length];
        distance = new int[G.edges.length];
        parent = new int[G.edges.length];


    }
    public int getMAXV(){return G.MAX_V;}
    public int[] getParent() {
        return parent;
    }

    public GraphCreation.Edge getEdge(int j) {return G.edges[j];}

    public int getQuantityVertices() {return quantityVertices;}

    public int getLength() {
        return G.edges.length;
    }




    public void readData() {

        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                Scanner scan = new Scanner(file);

                quantityVertices = scan.nextInt();
                if (quantityVertices <= 0) ;

                for (int i = 0; i < quantityVertices; ++i) {
                    int vertex_x = scan.nextInt();
                    if (vertex_x <= 0) ;

                    int numEdges = scan.nextInt();
                    if (numEdges < 0);

                    for (int j = 0; j < numEdges; ++j) {

                        int vertex_y = scan.nextInt();
                        if (vertex_y <= 0);

                        int _weight = scan.nextInt();
                        if (_weight <= 0);

                        G.addEdge(vertex_x, vertex_y, _weight);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void start(int s) {
        startVertex = s;
        for (int i = 1; i <= quantityVertices; ++i) {
            intree[i] = false;
            distance[i] = MAX_INT;
            parent[i] = -1;
        }

        distance[startVertex] = 0;
        v = startVertex;
    }


    public boolean executeStepAlgorithm() {

        if (!intree[v]) {
            int w;
            int weight;
            int dist;



            intree[v] = true;
            p = G.edges[v];
            while (p != null) {
                w = p.getY();
                if (w != 0) {

                    weight = p.getWeight();
                    if (distance[w] > weight && !intree[w]) {
                        distance[w] = weight;
                        parent[w] = v;

                    }
                }
                p = p.getNext();
            }
            v = 1;
            dist = MAX_INT;
            for (int i = 1; i <= quantityVertices; ++i) {
                if (!intree[i] && dist > distance[i] && parent[i] != -1) {
                    dist = distance[i];
                    v = i;
                }
            }


            return false;
        } else {

            return true;
        }
    }

    public void clear(){
        G.clear();
        startVertex = 0;
        quantityVertices = 0;
        for (int i = 0; i < parent.length; ++i){
            parent[i] = -1;
            intree[i] = false;
            distance[i] = MAX_INT;
        }
        p = null;
        v = 0;


    }
}

