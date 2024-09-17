import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class part5DijkstrasAlgorithm {

    public static int[][] matrix = new int[10][10];
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/data/dijkstrasData.txt");
        Scanner sc = new Scanner(file);

        int i = 0;
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
            i++;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("What node would you like to start at? (0 - 9)");
        int start = input.nextInt();
        System.out.println("What node would you like to end at? (0 - 9)");
        int end = input.nextInt();
        printShortestPath(start, end);
    }

    private static void printShortestPath(int start, int end) {
        HashMap<Integer, Integer> dist = new HashMap<>();
        HashMap<Integer, Integer> prev = new HashMap<>();

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        //for each node
        for (int n = 0; n < matrix.length; n++) {
            dist.put(n, Integer.MAX_VALUE);
            prev.put(n, null);
            q.add(n);
        }
        dist.put(start, 0);

        while (!q.isEmpty()){
            int u = q.remove();

            //for each neighbor of u in q
            for (int n = 0; n < matrix.length; n++) {
                int edgeWeight = matrix[u][n];
                if (edgeWeight != 0 && q.contains(n)){
                    int tempDist = dist.get(u) + edgeWeight;
                    if (tempDist < dist.get(n)){
                        dist.put(n, tempDist);
                        prev.put(n, u);

                        //update in queue
                        q.remove(n);
                        q.add(n);
                    }
                }
            }
        }
        System.out.println("The shortest length is "+ dist.get(end));

        //print path
        String path = String.valueOf(end);
        int lastNode = end;
        while (lastNode != start){
            lastNode = prev.get(lastNode);
            path = lastNode + " -> " + path;
        }
        System.out.println("The path is: " + path);
    }
}
