package main.java.logmonitoring.services;

import java.util.List;

public interface WriteReport <T> {
    public void write(List<T> result);
}
