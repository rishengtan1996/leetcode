import java.util.*;
/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
 * you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 * Example 3:
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 4:
 *
 * Input: n = 2, edges = [[0,1]]
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(0);
        if(n==2) return Arrays.asList(0,1);
        ArrayList<Integer> Graph[] = new ArrayList[n];
        int degree[] = new int[n];
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<edges.length; i++){
            if(Graph[edges[i][0]] == null){
                Graph[edges[i][0]] = new ArrayList<>();
            }if(Graph[edges[i][1]] == null){
                Graph[edges[i][1]] = new ArrayList<>();
            }
            Graph[edges[i][0]].add(edges[i][1]);
            Graph[edges[i][1]].add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        for(int i = 0; i<degree.length; i++){
            if(degree[i] == 1){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            ans = new ArrayList<>();/**这里是最精华的，怎样才能保证每次都能拿到最后几个queue里剩下的，就是每一次while的时候都建一条新的*/
            for(int i = 0; i<size; i++){
                int temp = queue.poll();
                ans.add(temp);
                for(int child: Graph[temp]){
                    degree[child]--;
                    if(degree[child]==1){
                        queue.offer(child);
                    }
                }
            }
        }
        return ans;
    }
}
