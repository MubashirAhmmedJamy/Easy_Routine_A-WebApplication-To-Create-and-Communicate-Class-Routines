package Beans;

import java.util.TreeMap;

public class ClassTimeMapper {

    private static TreeMap<String, Integer> tree_map = null;

    private static void setMap() {
        
        tree_map = new TreeMap<String, Integer>();
        
        tree_map.put("SUN_8AM",1);
        tree_map.put("SUN_9AM",2);
        tree_map.put("SUN_10AM",3);
        tree_map.put("SUN_11AM",4);
        tree_map.put("SUN_12PM",5);
        tree_map.put("SUN_2PM",6);
        tree_map.put("SUN_3PM",7);
        tree_map.put("SUN_4PM",8);
        tree_map.put("MON_8AM",9);
        tree_map.put("MON_9AM",10);
        tree_map.put("MON_10AM",11);
        tree_map.put("MON_11AM",12);
        tree_map.put("MON_12PM",13);
        tree_map.put("MON_2PM",14);
        tree_map.put("MON_3PM",15);
        tree_map.put("MON_4PM",16);
        tree_map.put("TUE_8AM",17);
        tree_map.put("TUE_9AM",18);
        tree_map.put("TUE_10AM",19);
        tree_map.put("TUE_11AM",20);
        tree_map.put("TUE_12PM",21);
        tree_map.put("TUE_2PM",22);
        tree_map.put("TUE_3PM",23);
        tree_map.put("TUE_4PM",24);
        tree_map.put("WED_8AM",25);
        tree_map.put("WED_9AM",26);
        tree_map.put("WED_10AM",27);
        tree_map.put("WED_11AM",28);
        tree_map.put("WED_12PM",29);
        tree_map.put("WED_2PM",30);
        tree_map.put("WED_3PM",31);
        tree_map.put("WED_4PM",32);
        tree_map.put("THU_8AM",33);
        tree_map.put("THU_9AM",34);
        tree_map.put("THU_10AM",35);
        tree_map.put("THU_11AM",36);
        tree_map.put("THU_12PM",37);
        tree_map.put("THU_2PM",38);
        tree_map.put("THU_3PM",39);
        tree_map.put("THU_4PM",40);
    }
    
    public static int getMap(String s){
        if(tree_map != null){
            return tree_map.get(s);
        }else{
            setMap();
            return tree_map.get(s);
        }
    }
}
