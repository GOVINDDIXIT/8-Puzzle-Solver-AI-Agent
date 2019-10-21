
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;


public class Solvers {
    
    public static enum SOLVE_METHOD{A_STAR};
   
    //to count the number of expanded nodes
    public static long times;
    
    //solve the current position with A* search algorithm
    public static Map<String, byte[]> aStar(byte[] current){
        PriorityQueue<State> q = new PriorityQueue<>();
        Map<String, Integer> dist = new HashMap<>();
        Map<String, byte[]> parent = new HashMap<>();
        
        times = 0;
        
        //initialize the distance of the current state to be 0
        dist.put(stringify(current), 0);
        
        //add the current state to the front of the states queue
        q.add(new State(current, 0));
        
        //A* Algorithm ...
        while(!q.isEmpty()){
            State crnt = q.poll();
            times++;
            if(Arrays.equals(crnt.getBoard(), BoardControl.GOAL)) break;            
            for(State child : crnt.getNextStates()){
                if(dist.getOrDefault(stringify(child.getBoard()), Integer.MAX_VALUE) > child.getCost()){                    
                    parent.put(stringify(child.getBoard()), crnt.getBoard());
                    dist.put(stringify(child.getBoard()), child.getCost());
                    q.add(child);
                }
            }
        }
        
        return parent;
    }
    
    
    //takes a byte array and returns it as a string for the map to hash
    //never hash arrays in java, they almost always return different hash values
    public static String stringify(byte[] arr){
        String str = "";
        for(int i = 0 ; i < arr.length ; ++i){
            str += String.valueOf(arr[i]);
        }
        return str;
    }
    
}
