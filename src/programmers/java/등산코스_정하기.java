package programmers.java;

import java.util.*;

public class 등산코스_정하기 {
    class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            int[] answer = { summits[0], Integer.MAX_VALUE };
            Map<Integer, SortedMap<Integer, Integer>> road = new HashMap<>();
            Set<Integer> summitSet = new HashSet<>();
            Set<Integer> gateSet = new HashSet<>();

            for (int summit : summits) { summitSet.add(summit); }
            for (int gate: gates) { gateSet.add(gate); }

            for (int[] path : paths) {
                road.computeIfAbsent(path[0], k -> new TreeMap<>())
                        .put(path[1], path[2]);
                road.computeIfAbsent(path[1], k -> new TreeMap<>())
                        .put(path[0], path[2]);
            }

            for (int summit: Arrays.stream(summits).sorted().toArray()) {
                Map<Integer, Integer> intensityMap = new HashMap<>();
                PriorityQueue<int[]> pq = new PriorityQueue<>(
                        Comparator.comparingInt((int[] a) -> a[1])
                                .thenComparingInt((int[] a) -> a[0])
                );

                pq.offer(new int[]{summit, 0});

                while (!pq.isEmpty()) {
                    int[] temp = pq.poll();

                    if (gateSet.contains(temp[0])) {
                        if (temp[1] < answer[1]) {
                            answer = temp.clone();
                            break;
                        }
                        continue;
                    }

                    for (Map.Entry<Integer, Integer> entry : road.get(temp[0]).entrySet()) {
                        int next = entry.getKey();
                        if (summitSet.contains(next)) continue;

                        int nextD = entry.getValue();
                        if (nextD >= answer[1]) continue;

                        int nextIntensity = Math.max(temp[1], nextD);
                        if (nextIntensity < intensityMap.getOrDefault(next, Integer.MAX_VALUE)) {
                            pq.offer(new int[]{next, nextIntensity});
                            intensityMap.put(next, nextIntensity);
                        }
                    }
                }
            }

            return answer;
        }
    }
}