import { Observable } from "rxjs";


let numbers = [1, 5, 10];

let source$ = Observable.create(observer => {
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
})
.delay(10000);

// Stuff to put the numbers in the html

document.getElementById('timerBtn').addEventListener('click', startTimer);
document.getElementById('resetBtn').addEventListener('click', resetTimer);
let valueNode = document.getElementById('values');

function startTimer() {
    source$.subscribe(
        value => {
            valueNode.innerHTML = valueNode.innerHTML + value + '<br/>';
        },
        error => {
            console.error(error)
        },
        () => {
            valueNode.innerHTML = valueNode.innerHTML + 'Done!';
        }
    );
}

function resetTimer() {
    valueNode.innerHTML = '';
}
