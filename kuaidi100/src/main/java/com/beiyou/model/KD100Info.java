package com.beiyou.model;

import com.beiyou.enums.KDStatus;
import lombok.Data;

import java.io.PrintStream;
import java.util.List;

@Data
public class KD100Info {

    private String message;

    private String state;

    public String getStateX() {
        return KDStatus.findByCode(Integer.valueOf(this.state)).getDesc();
    }

    private String stateX;


    private List<Step> data;

    @Data
    public static class  Step{
        String time;
        String ftime;
        String context;
    }
}
