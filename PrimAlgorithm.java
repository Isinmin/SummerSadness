
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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

                if (scan.hasNextInt()) {
                    quantityVertices = scan.nextInt();
                    if (quantityVertices <= 0 || quantityVertices >= 40 ) {
                        JOptionPane.showMessageDialog(null, "Ошибка! введите  количество вершин в диапазоне от 2 до 40! ");
                        throw new IllegalArgumentException();
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введены неккоректные данные!  ");
                    throw new IllegalArgumentException();
                }

                for (int i = 0; i < quantityVertices; ++i) {
int vertex_x;
                    if (scan.hasNextInt()) {
                         vertex_x = scan.nextInt();

                        if (vertex_x <= 0) {
                            JOptionPane.showMessageDialog(null, "Ошибка! Введите неотрицательный номер вершины! ");
                            throw new IllegalArgumentException();
                        }
                    }
else {
                        JOptionPane.showMessageDialog(null, "Ошибка! Введены некорректные данные! ");
                        throw new IllegalArgumentException();

                    }
                    int numEdges;
                    if(scan.hasNextInt()) {
                        numEdges = scan.nextInt();
                        if (numEdges < 0 || numEdges >=39) {
                            JOptionPane.showMessageDialog(null, "Ошибка! Введите неотрицательное количество смежных ребер! ");
                            throw new IllegalArgumentException();
                        }
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "Ошибка! Введены некорректные данные! ");
                        throw new IllegalArgumentException();


                    }

                    for (int j = 0; j < numEdges; ++j) {
int vertex_y;
if(scan.hasNextInt()) {
     vertex_y = scan.nextInt();

    if (vertex_y <= 0) {
        JOptionPane.showMessageDialog(null, "Ошибка! Введите неотрицательный номер инцидетной вершины ! ");
        throw new IllegalArgumentException();
    }
}
else {

    JOptionPane.showMessageDialog(null, "Ошибка! Введены некорректные данные! ");
    throw new IllegalArgumentException();
}
int _weight;
if (scan.hasNextInt()) {
    _weight = scan.nextInt();
    if (_weight <= 0 || _weight >= 1000) {
        JOptionPane.showMessageDialog(null, "Ошибка! Введите вес ребра в диапазоне от 0 до 1000! ");
        throw new IllegalArgumentException();
    }
}
else {

    JOptionPane.showMessageDialog(null, "Ошибка! Введены некорректные данные! ");
    throw new IllegalArgumentException();

}
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

