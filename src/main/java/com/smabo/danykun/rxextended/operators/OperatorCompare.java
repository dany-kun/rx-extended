package com.smabo.danykun.rxextended.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func2;

public class OperatorCompare<R, T> implements Observable.Operator<R, T> {

    private final Func2<? super T, ? super T, R> accumulator;

    private final T originalValue;
    private static final Object NO_INITIAL_VALUE = new Object();

    public OperatorCompare(T originalValue, Func2<? super T, ? super T, R> accumulator) {
        this.accumulator = accumulator;
        this.originalValue = originalValue;
    }

    public OperatorCompare(Func2<? super T, ? super T, R> accumulator) {
        //noinspection unchecked
        this((T) NO_INITIAL_VALUE,accumulator);
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        return new Subscriber<T>() {

            T previousValue = originalValue;

            @Override
            public void onNext(T t) {
                if (previousValue == NO_INITIAL_VALUE) {
                    subscriber.onNext((R)t);
                } else {
                    try {
                        R result = accumulator.call(previousValue, t);
                        subscriber.onNext(result);
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        onError(OnErrorThrowable.addValueAsLastCause(e, t));
                    }
                }
                previousValue = t;
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }

        };
    }
}
