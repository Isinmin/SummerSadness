
public interface GraphInterface {

//удаляет все вершины и рёбра из графа
    void clear();

/**
*добавление ребра в граф
* @param _x      начало дуги
* @param _y      конец дуги
* @param _weight вес дуги
*/
    void addEdge(int _x, int _y, int _weight);
}
