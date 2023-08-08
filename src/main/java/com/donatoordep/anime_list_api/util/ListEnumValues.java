package com.donatoordep.anime_list_api.util;

import java.util.List;

public class ListEnumValues {

    private ListEnumValues() {

    }


    public static String returnEnumValues(List<? extends Enum> values) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (i < values.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
