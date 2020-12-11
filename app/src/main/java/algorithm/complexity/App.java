/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package algorithm.complexity;

import algorithm.complexity.graphs.*;
import algorithm.complexity.search.LinearSearch;
import algorithm.complexity.search.BinarySearch;
import algorithm.complexity.search.SearchExecutor;
import algorithm.complexity.sort.BubbleSort;
import algorithm.complexity.sort.QuickSort;
import algorithm.complexity.sort.SortExecutor;

import java.lang.reflect.Array;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // bigger the algorithm
        // more time and space it will consume

        int searchArraySize = 1000000;
        int sortArraySize = 1000;
        int[] searchSample = generateSample(searchArraySize);
        int[] sortSample = generateSample(sortArraySize);

        System.out.println("\n\nUsing sample with " + searchArraySize + " positions for search.");
        System.out.println("\n\nUsing sample with " + sortArraySize + " positions for sort.");
        System.out.println("Running algorithms...");

        int value = 100544;

        // ----------------------------------------
        // O(n)
        // ----------------------------------------

        new SearchExecutor(new LinearSearch()).search(value, searchSample);

        // ----------------------------------------
        // O(n^2)
        // ----------------------------------------

        new SortExecutor(new BubbleSort()).sort(sortSample);

        // ----------------------------------------
        // O(log n)
        // ----------------------------------------

        // The key aspect here is that the array is already sorted
        int[] clonedArray = searchSample.clone();
        Arrays.sort(clonedArray);
        // binary search keeps splitting the the search in 2 (left and right)
        new SearchExecutor(new BinarySearch()).search(value, clonedArray);

        // ----------------------------------------
        // O(n log n)
        // ----------------------------------------

        /*
        * comparisons = log n!
        * comparisons = log n + log(n-1) + ... + log (1)
        * comparisons = n log n
        * */

        // less time shifting numbers.. more efficiently

        new SortExecutor(new QuickSort()).sort(sortSample);

        // ----------------------------------------
        // O(E log V)
        // ----------------------------------------

        List<Vertex> nodes = generateNodes(11);
        List<Edge> edges = generateEdges(nodes);
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        dijkstra.execute(nodes.get(0));

        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

    private static List<Edge> generateEdges(List<Vertex> nodes) {
        List<Edge> edges = new ArrayList<Edge>();

        edges.add(new Edge("Edge_0", nodes.get(0), nodes.get(1), 85));
        edges.add(new Edge("Edge_1", nodes.get(0), nodes.get(2), 217));
        edges.add(new Edge("Edge_2", nodes.get(0), nodes.get(4), 173));
        edges.add(new Edge("Edge_3", nodes.get(2), nodes.get(6), 186));
        edges.add(new Edge("Edge_4", nodes.get(2), nodes.get(7), 103));
        edges.add(new Edge("Edge_5", nodes.get(3), nodes.get(7), 183));
        edges.add(new Edge("Edge_6", nodes.get(5), nodes.get(8), 250));
        edges.add(new Edge("Edge_7", nodes.get(8), nodes.get(9), 84));
        edges.add(new Edge("Edge_8", nodes.get(7), nodes.get(9), 167));
        edges.add(new Edge("Edge_9", nodes.get(4), nodes.get(9), 502));
        edges.add(new Edge("Edge_10", nodes.get(9), nodes.get(10), 40));
        edges.add(new Edge("Edge_11", nodes.get(1), nodes.get(10), 600));

        return edges;
    }

    private static List<Vertex> generateNodes(int size) {
        ArrayList<Vertex> nodes = new ArrayList<Vertex>();
        for (int i = 0; i < size; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        return nodes;
    }

    private static int[] generateSample(int sizeForSearch) {
        int[] arrayForSearch = generateArray(sizeForSearch);
        shuffle(arrayForSearch);
        return arrayForSearch;
    }

    private static void shuffle(int[] array) {
        final Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = random.nextInt(i + 1);
            int v = array[index];
            array[index] = array[i];
            array[i] = v;
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        return array;
    }
}
