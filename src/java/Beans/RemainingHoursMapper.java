package Beans;

import java.util.TreeMap;

public class RemainingHoursMapper {

    private static TreeMap<String, Integer> tree_map = null;

    private static void setMap() {

        tree_map = new TreeMap<String, Integer>();

        tree_map.put("SUN_8AM", 8);
        tree_map.put("SUN_9AM", 7);
        tree_map.put("SUN_10AM", 6);
        tree_map.put("SUN_11AM", 5);
        tree_map.put("SUN_12PM", 4);
        tree_map.put("SUN_2PM", 3);
        tree_map.put("SUN_3PM", 2);
        tree_map.put("SUN_4PM", 1);
        tree_map.put("MON_8AM", 8);
        tree_map.put("MON_9AM", 7);
        tree_map.put("MON_10AM", 6);
        tree_map.put("MON_11AM", 5);
        tree_map.put("MON_12PM", 4);
        tree_map.put("MON_2PM", 3);
        tree_map.put("MON_3PM", 2);
        tree_map.put("MON_4PM", 1);
        tree_map.put("TUE_8AM", 8);
        tree_map.put("TUE_9AM", 7);
        tree_map.put("TUE_10AM", 6);
        tree_map.put("TUE_11AM", 5);
        tree_map.put("TUE_12PM", 4);
        tree_map.put("TUE_2PM", 3);
        tree_map.put("TUE_3PM", 2);
        tree_map.put("TUE_4PM", 1);
        tree_map.put("WED_8AM", 8);
        tree_map.put("WED_9AM", 7);
        tree_map.put("WED_10AM", 6);
        tree_map.put("WED_11AM", 5);
        tree_map.put("WED_12PM", 4);
        tree_map.put("WED_2PM", 3);
        tree_map.put("WED_3PM", 2);
        tree_map.put("WED_4PM", 1);
        tree_map.put("THU_8AM", 8);
        tree_map.put("THU_9AM", 7);
        tree_map.put("THU_10AM", 6);
        tree_map.put("THU_11AM", 5);
        tree_map.put("THU_12PM", 4);
        tree_map.put("THU_2PM", 3);
        tree_map.put("THU_3PM", 2);
        tree_map.put("THU_4PM", 1);
    }
    
    public static int getRem(String s){
        if(tree_map != null){
            return tree_map.get(s);
        }else{
            setMap();
            return tree_map.get(s);
        }
    }
}
