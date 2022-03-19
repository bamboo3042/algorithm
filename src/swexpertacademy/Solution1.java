package swexpertacademy;
import java.util.*;

public class Solution1 {

    public class node{
        int t1, t2, a, b;
        String c;
    }

    public Queue<node> wait_read_queue = new LinkedList<>();
    public Queue<node> wait_write_queue = new LinkedList<>();
    public Queue<node> app_read_queue = new LinkedList<>();
    public Queue<node> app_write_queue = new LinkedList<>();

    public node make_node(String process){
        String[] s = process.split(" ");
        node temp = new node();
        temp.t1 = Integer.parseInt(s[1]);
        temp.t2 = Integer.parseInt(s[2]);
        temp.a = Integer.parseInt(s[3]);
        temp.b = Integer.parseInt(s[4]);
        temp.c = s.length == 6 ? s[5] : null;
        System.out.println("t1 : " + temp.t1 + ", t2 : " + temp.t2 + ", a : " + temp.a + ", b : " + temp.b + ", c : " + temp.c);
        return temp;
    }

    public void init(String[] processes){
        for(String process : processes){
            node temp = make_node(process);
            if(process.contains("read")) wait_read_queue.add(temp);
            else wait_write_queue.add(temp);
        }
    }

    public void print_node(node temp){
        if(temp == null) System.out.println("node is empty");
        else System.out.println("t1 : " + temp.t1 + ", t2 : " + temp.t2 + ", a : " + temp.a + ", b : " + temp.b + ", c : " + temp.c);
    }

    public String[] solve(String[] arr){
        int t = 1;
        String[] answer = new String[wait_read_queue.size()];
        int n = 0;
        while (!app_write_queue.isEmpty() || !app_read_queue.isEmpty() || !wait_read_queue.isEmpty() || !wait_write_queue.isEmpty()) {
            if (!app_write_queue.isEmpty()) {
                if (app_write_queue.peek().t2 == t) app_write_queue.poll();
                t++;
                continue;
            } else if (!app_read_queue.isEmpty()) {
                for (Iterator<node> iter = app_read_queue.iterator(); iter.hasNext(); ) {
                    node temp = iter.next();
                    if (temp.t2 == t) {
                        answer[n] = "";
                        for(int i = temp.a; i <= temp.b; i++){
                            answer[n] += arr[i];
                        }
                        n += 1;
                        iter.remove();
                    }
                }
            }
            if (app_read_queue.isEmpty() && app_write_queue.isEmpty() && !wait_write_queue.isEmpty() && wait_write_queue.peek().t1 <= t) {
                node temp = wait_write_queue.poll();
                temp.t2 = t + temp.t2;
                for (int i = temp.a; i <= temp.b; i++) {
                    arr[i] = temp.c;
                }
                app_write_queue.add(temp);
            } else if (app_write_queue.isEmpty() && (wait_write_queue.isEmpty() || wait_write_queue.peek().t1 > t) && !wait_read_queue.isEmpty() && wait_read_queue.peek().t1 <= t) {
                for(Iterator<node> iter = wait_read_queue.iterator(); iter.hasNext(); ){
                    node temp = iter.next();
                    if(temp.t1 <= t){
                        temp.t2 = t + temp.t2;
                        app_read_queue.add(temp);
                        iter.remove();
                    }
                }
            }
            t++;
        }
        answer[n] = String.valueOf(t);
        return answer;
    }

    public String[] solution(String[] arr, String[] processes) {
        String[] answer = {};
        init(processes);
        answer = solve(arr);
        return answer;
    }
}
