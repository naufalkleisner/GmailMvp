package com.nbs.starter.base;

/**
 * Created by ghiyatshanif on 4/17/17.
 */

public interface DataMapper<T, E> {

    T transform(E model);
}
