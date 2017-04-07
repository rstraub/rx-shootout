import { Observable } from 'rxjs';

let paragraph = document.getElementById('hook');
console.log(paragraph)
let source = Observable.fromEvent(document, 'click')
    .map((clickEvent: MouseEvent ) => {
        return {
            x: clickEvent.clientX,
            y: clickEvent.clientY
        }
    })
    .delay(200);

source.subscribe(
    value => { onNext(value) },
    error => { console.error('oops') },
    () => { console.log('done!') }
);

function onNext(value) {
    paragraph.style.color = 'red'
    paragraph.style.left = value.x + 'px';
    paragraph.style.top = value.y + 'px';
}
