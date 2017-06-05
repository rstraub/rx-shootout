package nl.edegier.timer;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Erwin on 05/06/2017.
 */
public class Timer {
    static int index = 0;

    public static void main(String[] args) throws InterruptedException {
        final int[] numbers = {1, 5, 10};
       Observable obs1 = Observable.create(observer ->{
           Observable.interval(1000, TimeUnit.MILLISECONDS).subscribe(interval ->{
               if(index < numbers.length){
                   observer.onNext(numbers[index++]);
               } else {
                   observer.onComplete();
               }
           });
        });

        obs1.subscribe(value ->{
            System.out.println(value);
        });

        Thread.sleep(1000000);
    }
}
