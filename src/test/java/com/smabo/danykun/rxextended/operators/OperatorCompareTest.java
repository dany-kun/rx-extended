package com.smabo.danykun.rxextended.operators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.functions.Func2;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OperatorCompareTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testCompareOperatorInitialValue() throws Exception {
        @SuppressWarnings("unchecked") rx.Observer<Integer> observer = mock(rx.Observer.class);

        Observable<Integer> observable = Observable.just(1, 2, 3);

        OperatorCompare<Integer, Integer> operatorCompare = new OperatorCompare<Integer, Integer>(10, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        observable.lift(operatorCompare).subscribe(observer);

        verify(observer, never()).onError(any(Throwable.class));
        verify(observer, times(1)).onNext(11);
        verify(observer, times(1)).onNext(3);
        verify(observer, times(1)).onNext(5);
        verify(observer, times(1)).onCompleted();
    }

    @Test
    public void testCompareOperatorNoInitialValue() throws Exception {
        @SuppressWarnings("unchecked") rx.Observer<Integer> observer = mock(rx.Observer.class);

        Observable<Integer> observable = Observable.just(1, 2, 3);

        OperatorCompare<Integer, Integer> operatorCompare = new OperatorCompare<Integer, Integer>(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        observable.lift(operatorCompare).subscribe(observer);

        verify(observer, never()).onError(any(Throwable.class));
        verify(observer, times(1)).onNext(1);
        verify(observer, times(1)).onNext(3);
        verify(observer, times(1)).onNext(5);
        verify(observer, times(1)).onCompleted();
    }
}