package com.android.project.moviebox.Interfaces;

/**
 * Common error class Interface for handling all kind of errors
 */
public interface IView {

    void error(Throwable e);

    void error(String msg);
}