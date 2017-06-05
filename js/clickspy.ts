import { Observable } from "rxjs";

let ball = document.getElementById('hook');
let subscription;
let canvas = document.getElementsByClassName('slide-background present');

document.getElementById('spyBtn').addEventListener('click', startSpy);
document.getElementById('stopBtn').addEventListener('click', stopSpy);

let source$ = Observable.fromEvent(canvas, 'mousemove')
    .map((clickEvent: MouseEvent) => {
        return {
            x: clickEvent.clientX,
            y: clickEvent.clientY
        }
    })
    .delay(200);

function startSpy() {
    subscription = source$.subscribe(
        value => {
            onNext(value)
        },
        error => {
            console.error('oops')
        },
        () => {
            console.log('done!')
        }
    );
}

function stopSpy() {
    ball.style.backgroundColor = 'transparent';
    subscription.unsubscribe();
}

function onNext(value) {
    ball.style.backgroundColor = 'red';
    ball.style.left = value.x + 'px';
    ball.style.top = value.y + 'px';
}
