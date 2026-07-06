package com.dneumann.raben1.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Item {
    private int id;
    private List<String> values;
}
