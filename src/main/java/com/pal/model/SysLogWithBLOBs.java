package com.pal.model;

import lombok.Data;

@Data
public class SysLogWithBLOBs extends SysLog {
    private String oldValue;

    private String newValue;
}