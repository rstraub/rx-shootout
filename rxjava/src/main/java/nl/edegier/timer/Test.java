package nl.edegier.timer;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Erwin on 20/10/2017.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1000, TimeUnit.MILLISECONDS).filter( i -> i%2==0).switchIfEmpty(Observable.just()).subscribe(System.out::println);

        Thread.sleep(1000000);
    }
}
