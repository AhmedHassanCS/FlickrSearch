package com.apps.ahfreelancing.flickersearch.domain.interactor;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public abstract class BaseInteractor<Param, T> {
    Scheduler observeOn;
    Scheduler subscribeOn;
    BaseInteractor(Scheduler observeOn, Scheduler subscribeOn){
        this.observeOn = observeOn;
        this.subscribeOn = subscribeOn;
    }
    protected abstract void execute(SingleObserver<T> observer, Param param);
}
