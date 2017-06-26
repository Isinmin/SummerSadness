


public class GraphCreation implements GraphInterface {
    protected final static int MAX_V = 40;

    public static class Edge {
        private int x;
        private int y;
        private int weight;
        private Edge next;

        public Edge(int _x, int _y, int _weight) {
            x = _x;
            y = _y;
            weight = _weight;
            next = null;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWeight() {
            return weight;
        }

        public Edge getNext() {
            return next;
        }
    }

    public Edge[] edges;


    public GraphCreation() {
        edges = new Edge[MAX_V + 1];
        clear();
    }

    public void clear() {
        for (int i = 0; i < edges.length; ++i) {
            Edge e = new Edge(0, 0, 0);
            edges[i] = e;
        }
    }

    public void addEdge(int _x, int _y, int _weight) {
        Edge e = new Edge(_x, _y, _weight);

        e.next = edges[_x];
        edges[_x] = e;

        Edge _e = new Edge(_y, _x, _weight);

        _e.next = edges[_y];
        edges[_y] = _e;
    }
}
