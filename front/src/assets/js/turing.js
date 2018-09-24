document.addEventListener('DOMContentLoaded', () =>{
    clickNumber();
    sendNumber();
});

const clickNumber = () => {
    
    var el = document.querySelector('.turing__calling-number');
    document.querySelectorAll('.turing__phone-num').forEach((e) => {
        e.addEventListener('click', (item) => {
            const text = item.currentTarget.childNodes[1].childNodes[0].nodeValue;
            el.innerHTML = el.innerHTML + text;
        }, false);
    });
}


const sendNumber = () => {
    var retrieve = document.querySelector('.turing__button-call'),
    toReadyStateDescription = function (state) {
        switch (state) {
            case 0:
                return 'UNSENT';
            case 1:
                return 'OPENED';
            case 2:
                return 'HEADERS_RECEIVED';
            case 3:
                return 'LOADING';
            case 4:
                return 'DONE';
            default:
                return '';
        }
    };
    retrieve.addEventListener('click', function (e) {
        var el = document.querySelector('.turing__calling-number').innerText,
        url = `http://localhost:8080/maquina_turing/call?numbers=${el}`;
        var oReq = new XMLHttpRequest();
        oReq.onload = function () {
            console.log('Inside the onload event');
        };
        oReq.onreadystatechange = function () {
            console.log('Inside the onreadystatechange event with readyState: ' +
                toReadyStateDescription(oReq.readyState));
        };
        oReq.open('GET', url, true);
        oReq.send();
    });
}