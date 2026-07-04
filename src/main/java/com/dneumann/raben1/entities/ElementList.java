package com.dneumann.raben1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ElementList {
    List<String> columnOrder;
    List<Item> elements;
}
