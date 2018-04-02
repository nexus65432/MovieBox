package com.android.project.moviebox.BaseClass;

import com.android.project.moviebox.Interfaces.IView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on 3/23/18.
 */
public class BaseViewModel<T extends IView> {

    protected CompositeDisposable compositeDisposable;
    protected T view;

    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }

    public void clearSubscriptions() {
        compositeDisposable.clear();
    }
}
