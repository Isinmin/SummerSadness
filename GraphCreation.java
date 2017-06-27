
/**
 * Класс GraphСreation представляет собой неориетированный граф, для
 * реализации которого использовалась обощенная коллекция ArrayList.
 * В ней содержатся элементы вложенного класса Edge, представляющего
 * из себя ребро графа.
 * Класс поддерживает операции добавления ребра и очистки графа.
 * Операция clear работает за константное время. Операция addEdge работает
 * работает за время O(V).
 *
 */

public class GraphCreation implements GraphInterface {
    protected final static int MAX_V = 40;

//встроенный класс ребро
    public static class Edge {
        private int x;
        private int y;
        private int weight;
        private Edge next;

/**
*@param _x -начало дуги
*@param _y -конец дуги
*@param _weight - вес
*конструктор ребра
*/
        public Edge(int _x, int _y, int _weight) {
            x = _x;
            y = _y;
            weight = _weight;
            next = null;
        }
//получение начала дуги
        public int getX() {
            return x;
        }
//получение конца дуги
        public int getY() {
            return y;
        }
//получение веса
        public int getWeight() {
            return weight;
        }
//получение нового ребра
        public Edge getNext() {
            return next;
        }
    }
//массив рёбер
    public Edge[] edges;

//инициализация пустого графа
    public GraphCreation() {
        edges = new Edge[MAX_V + 1];
        clear();
    }

//очистка графа
    public void clear() {
        for (int i = 0; i < edges.length; ++i) {
            Edge e = new Edge(0, 0, 0);
            edges[i] = e;
        }
    }

/**
*добавление ребра в граф
* @param _x      начало дуги
* @param _y      конец дуги
* @param _weight вес дуги
*/

    public void addEdge(int _x, int _y, int _weight) {
        Edge e = new Edge(_x, _y, _weight);

        e.next = edges[_x];
        edges[_x] = e;

        Edge _e = new Edge(_y, _x, _weight);

        _e.next = edges[_y];
        edges[_y] = _e;
    }
}
