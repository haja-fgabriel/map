package ubb.scs.map.ir.seminar.studentsmanagement.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericModel {
    private ArrayList<String> data;

    public GenericModel(String... args) {
        this.data = new ArrayList<>(Arrays.asList(args));
    }

    public String get0() {
        return data.get(0);
    }

    public String get1() {
        return data.get(1);
    }

    public String get2() {
        return data.get(2);

    }

    public String get3() {
        return data.get(3);
    }
}
