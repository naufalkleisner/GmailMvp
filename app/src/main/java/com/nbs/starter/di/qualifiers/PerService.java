package com.nbs.starter.di.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ghiyatshanif on 2/27/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
