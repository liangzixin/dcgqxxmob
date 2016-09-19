package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016-08-17.
 */
public enum Room {
    ROOM1{
        @Override
        public String getName() {
            return "单人间";
        }
    },
    ROOM2{
        @Override
        public String getName() {
            return "标准间";
        }
    },
    ROOM3{
        @Override
        public String getName() {
            return "商务套间";
        }
    },
    ROOM4{
        @Override
        public String getName() {
            return "行政间";
        }
    },
    ROOM5{
        @Override
        public String getName() {
            return "标准三人间";
        }
    },
    ROOM6{
        @Override
        public String getName() {
            return "大床房";
        }
    },
    ROOM7{
        @Override
        public String getName() {
            return "豪华套房";
        }
    },
    ROOM8{
        @Override
        public String getName() {
            return "特价促销房";
        }
    },
    ROOM9{
        @Override
        public String getName() {
            return "其它";
        }
    };
    /**
     * 获取名称的抽象方法
     * @return 名称
     */
    public abstract String getName();
    public static List<String> getValues(){
        List<String> list = new ArrayList<String>();
        for (Room room : Room.values()) {
            list.add(room.getName());
        }
        return list;
    }
}
