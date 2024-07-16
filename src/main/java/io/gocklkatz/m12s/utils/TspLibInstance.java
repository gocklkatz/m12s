package io.gocklkatz.m12s.utils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "travellingSalesmanProblemInstance")
public class TspLibInstance {

    private String name;

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
