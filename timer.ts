import { Observable } from 'rxjs';

let numbers = [1, 5, 10];

let source = Observable.create(observer => {
    let index = 0;
    let produceValue = () => {
        observer.next(numbers[index++]);
        if (index < numbers.length) {
            setTimeout(produceValue, 1000);
        } else {
            observer.complete();
        }
    };

    produceValue();
}).map(n => n * 2)
    .filter(n => n > 4);

source.subscribe(
    value => { console.log(`value: ${value}`) },
    error => { console.error(error) },
    () => { console.log('done') }
);
