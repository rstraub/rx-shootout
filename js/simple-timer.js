const Observable = require("rxjs").Observable;

let numbers = [1, 1, 2, 3, 5, 8, 11]; // Our "async" data

// Observable
const source$ = Observable.create(observer => {
  let index = 0;

  // Iterator through the numbers and output one each second until we have reached the end
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
  value => console.log(value), // onNext
  error => console.error(error), // onError
  () => console.log("done") // onComplete
);
