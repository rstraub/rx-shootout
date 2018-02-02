const Observable = require("rxjs").Observable;

let numbers = [1, 1, 2, 3, 5, 8, 11];

// Observable
const source$ = Observable.create(observer => {
  let index = 0;
  const produceValue = () => {
    observer.next(numbers[index++]);
    if (index < numbers.length) {
      setTimeout(produceValue, 1000);
    } else {
      observer.complete();
    }
  };

  produceValue();
});

// Observer
source$.subscribe(
  value => console.log(value),
  error => console.error(error),
  () => console.log("done")
);
