package Beans;
public class SerialMapper {

    private static String serial[] = null;

    private static void setMap() {
        serial = new String[100];
        serial[1] = "SUN_8AM";
        serial[2] = "SUN_9AM";
        serial[3] = "SUN_10AM";
        serial[4] = "SUN_11AM";
        serial[5] = "SUN_12PM";
        serial[6] = "SUN_2PM";
        serial[7] = "SUN_3PM";
        serial[8] = "SUN_4PM";
        serial[9] = "MON_8AM";
        serial[10] = "MON_9AM";
        serial[11] = "MON_10AM";
        serial[12] = "MON_11AM";
        serial[13] = "MON_12PM";
        serial[14] = "MON_2PM";
        serial[15] = "MON_3PM";
        serial[16] = "MON_4PM";
        serial[17] = "TUE_8AM";
        serial[18] = "TUE_9AM";
        serial[19] = "TUE_10AM";
        serial[20] = "TUE_11AM";
        serial[21] = "TUE_12PM";
        serial[22] = "TUE_2PM";
        serial[23] = "TUE_3PM";
        serial[24] = "TUE_4PM";
        serial[25] = "WED_8AM";
        serial[26] = "WED_9AM";
        serial[27] = "WED_10AM";
        serial[28] = "WED_11AM";
        serial[29] = "WED_12PM";
        serial[30] = "WED_2PM";
        serial[31] = "WED_3PM";
        serial[32] = "WED_4PM";
        serial[33] = "THU_8AM";
        serial[34] = "THU_9AM";
        serial[35] = "THU_10AM";
        serial[36] = "THU_11AM";
        serial[37] = "THU_12PM";
        serial[38] = "THU_2PM";
        serial[39] = "THU_3PM";
        serial[40] = "THU_4PM";

    }

    public static String getNext(int x) {
        if (serial != null) {
            return serial[x];
        } else {
            setMap();
            return serial[x];
        }
    }
}
