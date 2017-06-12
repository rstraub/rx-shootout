package nl.edegier.timer;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Erwin on 05/06/2017.
 */
public class TimerImproved {

    public static void main(String[] args) throws InterruptedException {
        final int[] numbers = {1, 5, 10};

        Observable observable = Observable.interval(1000, TimeUnit.MILLISECONDS).take(numbers.length).map(t -> numbers[t.intValue()]);

        observable.subscribe(value -> System.out.println(value));

        Thread.sleep(1000000);
    }
}
