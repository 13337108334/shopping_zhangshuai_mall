package tree;

import java.util.*;

/**
 * 多图遍历算法实现
 * 功能：找到所有起点到终点的路径中耗时最短的路径
 * 核心思路
 * 找到所有起点：通过计算节点的入度，入度为0的节点即为起点
 *
 * 遍历所有路径：对每个起点使用DFS遍历所有可能的终点路径
 *
 * 计算路径耗时：累加路径上所有节点的耗时
 *
 * 选择最短路径：比较所有路径的耗时，选择耗时最短的路径
 *
 * 关键特性
 * 多起点支持：能够处理图中有多个起点的情况
 *
 * DFS遍历：使用深度优先搜索找到所有完整路径
 *
 * 路径回溯：在DFS过程中正确管理路径状态
 *
 * 耗时计算：准确计算每条路径的总耗时
 *
 * 时间复杂度
 * 时间复杂度：O(N + E)，其中N是节点数，E是边数
 *
 * 空间复杂度：O(N)，用于存储路径和递归栈
 *
 * 测试用例覆盖
 * 基本图结构测试
 *
 * 多起点场景测试
 *
 * 单节点图测试
 *
 * 复杂图结构测试
 *
 */
public class GraphTraversal {

    /**
     * 实现一个对多个图的遍历算法（深度优先 或者 广度优先 算法），编程语言不限：
     *
     * public List<String> getShortestInvocationChain(Map<String, List<String>> serviceInvocations, Map<String, Integer> invocationsTime)
     *
     * 其中入参 serviceInvocations 形如 { "a": ["b", "c", "d"], "b": ["d", "e"], "e": ["f"], ... }  表示图中节点之间的关系，例如 a 后面是 b、c、d 节点，
     * 入参 invocationsTime 形如 { "a": 10, "b": 20, "c": 30 } 表示节点自身的耗时，
     *
     * 请先求出多图中的所有起点（入度为 0 的点），再求出所有起点和终点线段里耗时最短的起点到终点线路，无需考虑中间链路，最后将最短的一条起点到终点链路返回（形式：[a,b,c] 这种数组形式）。
     *
     * 可以使用本地 IDE，并充分补充单元测试和代码注释。你本地 IDE 跑通了，注释补全了可以直接发我微信。题目有问题可以电话我。
     */
    /**
     * 获取最短调用链
     * @param serviceInvocations 服务调用关系图，格式：{ "a": ["b", "c", "d"], "b": ["d", "e"], ... }
     * @param invocationsTime 服务调用耗时，格式：{ "a": 10, "b": 20, "c": 30, ... }
     * @return 耗时最短的起点到终点路径，格式：[a, b, c]
     */
    public List<String> getShortestInvocationChain(
            Map<String, List<String>> serviceInvocations,
            Map<String, Integer> invocationsTime) {

        // 1. 输入验证
        if (serviceInvocations == null || serviceInvocations.isEmpty() ||
                invocationsTime == null || invocationsTime.isEmpty()) {
            return new ArrayList<>();
        }

        // 2. 找到所有起点（入度为0的节点）
        Set<String> startNodes = findStartNodes(serviceInvocations);
        if (startNodes.isEmpty()) {
            return new ArrayList<>();
        }

        System.out.println("找到起点: " + startNodes);

        // 3. 为每个起点找到所有终点路径，并计算总耗时
        List<PathInfo> allPaths = new ArrayList<>();
        for (String startNode : startNodes) {
            List<PathInfo> pathsFromStart = findAllPathsFromStart(
                    startNode, serviceInvocations, invocationsTime);
            allPaths.addAll(pathsFromStart);
        }

        // 4. 找到耗时最短的路径
        if (allPaths.isEmpty()) {
            return new ArrayList<>();
        }

        PathInfo shortestPath = findShortestTimePath(allPaths);
        System.out.println("最短路径: " + shortestPath.path + ", 耗时: " + shortestPath.totalTime);

        return shortestPath.path;
    }

    /**
     * 找到所有起点（入度为0的节点）
     */
    private Set<String> findStartNodes(Map<String, List<String>> serviceInvocations) {
        Set<String> allNodes = new HashSet<>();
        Set<String> hasIncomingEdges = new HashSet<>();

        // 收集所有节点和入边关系
        for (Map.Entry<String, List<String>> entry : serviceInvocations.entrySet()) {
            String node = entry.getKey();
            List<String> neighbors = entry.getValue();

            allNodes.add(node);
            hasIncomingEdges.addAll(neighbors);
        }

        // 入度为0的节点 = 所有节点 - 有入边的节点
        Set<String> startNodes = new HashSet<>(allNodes);
        startNodes.removeAll(hasIncomingEdges);

        return startNodes;
    }

    /**
     * 从起点开始找到所有到终点的路径
     */
    private List<PathInfo> findAllPathsFromStart(
            String startNode,
            Map<String, List<String>> serviceInvocations,
            Map<String, Integer> invocationsTime) {

        List<PathInfo> result = new ArrayList<>();
        // 使用DFS遍历所有路径
        dfs(startNode, new ArrayList<>(), result, serviceInvocations, invocationsTime);
        return result;
    }

    /**
     * 深度优先搜索遍历所有路径
     */
    private void dfs(
            String currentNode,
            List<String> currentPath,
            List<PathInfo> result,
            Map<String, List<String>> serviceInvocations,
            Map<String, Integer> invocationsTime) {

        // 添加当前节点到路径
        currentPath.add(currentNode);

        // 获取当前节点的邻居
        List<String> neighbors = serviceInvocations.get(currentNode);

        // 如果没有邻居，说明是终点
        if (neighbors == null || neighbors.isEmpty()) {
            // 计算路径总耗时
            int totalTime = calculatePathTime(currentPath, invocationsTime);
            result.add(new PathInfo(new ArrayList<>(currentPath), totalTime));
        } else {
            // 继续遍历邻居节点
            for (String neighbor : neighbors) {
                dfs(neighbor, currentPath, result, serviceInvocations, invocationsTime);
            }
        }

        // 回溯，移除当前节点
        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * 计算路径总耗时
     */
    private int calculatePathTime(List<String> path, Map<String, Integer> invocationsTime) {
        int totalTime = 0;
        for (String node : path) {
            totalTime += invocationsTime.getOrDefault(node, 0);
        }
        return totalTime;
    }

    /**
     * 找到耗时最短的路径
     */
    private PathInfo findShortestTimePath(List<PathInfo> allPaths) {
        PathInfo shortest = allPaths.get(0);
        for (PathInfo path : allPaths) {
            if (path.totalTime < shortest.totalTime) {
                shortest = path;
            }
        }
        return shortest;
    }

    /**
     * 路径信息内部类
     */
    private static class PathInfo {
        List<String> path;      // 路径节点列表
        int totalTime;          // 路径总耗时

        PathInfo(List<String> path, int totalTime) {
            this.path = path;
            this.totalTime = totalTime;
        }
    }

    // ========== 单元测试 ==========

    public static void main(String[] args) {
        GraphTraversal solver = new GraphTraversal();

        // 测试用例1：基本测试
        testCase1(solver);

        // 测试用例2：多个起点
        testCase2(solver);

        // 测试用例3：单节点图
        testCase3(solver);

        // 测试用例4：复杂图结构
        testCase4(solver);
    }

    private static void testCase1(GraphTraversal solver) {
        System.out.println("=== 测试用例1: 基本测试 ===");

        Map<String, List<String>> serviceInvocations = new HashMap<>();
        serviceInvocations.put("a", Arrays.asList("b", "c"));
        serviceInvocations.put("b", Arrays.asList("d"));
        serviceInvocations.put("c", Arrays.asList("d"));

        Map<String, Integer> invocationsTime = new HashMap<>();
        invocationsTime.put("a", 10);
        invocationsTime.put("b", 20);
        invocationsTime.put("c", 5);
        invocationsTime.put("d", 15);

        List<String> result = solver.getShortestInvocationChain(serviceInvocations, invocationsTime);
        System.out.println("结果: " + result);
        System.out.println("预期: [a, c, d] (总耗时: 30)\n");
    }

    private static void testCase2(GraphTraversal solver) {
        System.out.println("=== 测试用例2: 多个起点 ===");

        Map<String, List<String>> serviceInvocations = new HashMap<>();
        serviceInvocations.put("a", Arrays.asList("c"));
        serviceInvocations.put("b", Arrays.asList("c"));
        serviceInvocations.put("c", Arrays.asList("d"));

        Map<String, Integer> invocationsTime = new HashMap<>();
        invocationsTime.put("a", 5);
        invocationsTime.put("b", 10);
        invocationsTime.put("c", 15);
        invocationsTime.put("d", 20);

        List<String> result = solver.getShortestInvocationChain(serviceInvocations, invocationsTime);
        System.out.println("结果: " + result);
        System.out.println("预期: [a, c, d] (总耗时: 40)\n");
    }

    private static void testCase3(GraphTraversal solver) {
        System.out.println("=== 测试用例3: 单节点图 ===");

        Map<String, List<String>> serviceInvocations = new HashMap<>();
        serviceInvocations.put("a", new ArrayList<>());

        Map<String, Integer> invocationsTime = new HashMap<>();
        invocationsTime.put("a", 10);

        List<String> result = solver.getShortestInvocationChain(serviceInvocations, invocationsTime);
        System.out.println("结果: " + result);
        System.out.println("预期: [a] (总耗时: 10)\n");
    }

    private static void testCase4(GraphTraversal solver) {
        System.out.println("=== 测试用例4: 复杂图结构 ===");

        Map<String, List<String>> serviceInvocations = new HashMap<>();
        serviceInvocations.put("start1", Arrays.asList("a", "b"));
        serviceInvocations.put("start2", Arrays.asList("c"));
        serviceInvocations.put("a", Arrays.asList("d"));
        serviceInvocations.put("b", Arrays.asList("d", "e"));
        serviceInvocations.put("c", Arrays.asList("f"));
        serviceInvocations.put("d", Arrays.asList("end"));
        serviceInvocations.put("e", Arrays.asList("end"));
        serviceInvocations.put("f", Arrays.asList("end"));

        Map<String, Integer> invocationsTime = new HashMap<>();
        invocationsTime.put("start1", 5);
        invocationsTime.put("start2", 2);
        invocationsTime.put("a", 10);
        invocationsTime.put("b", 3);
        invocationsTime.put("c", 8);
        invocationsTime.put("d", 4);
        invocationsTime.put("e", 6);
        invocationsTime.put("f", 12);
        invocationsTime.put("end", 1);

        List<String> result = solver.getShortestInvocationChain(serviceInvocations, invocationsTime);
        System.out.println("结果: " + result);
        System.out.println("预期: [start2, c, f, end] 或其他最短路径\n");
    }
}
