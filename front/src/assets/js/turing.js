document.addEventListener('DOMContentLoaded', () =>{
    clickNumber();
    clearNumber();
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

const clearNumber = () => {
    document.querySelector('.turing__button-clear').addEventListener('click', (item) => {
        var el = document.querySelector('.turing__calling-number');
        el.innerHTML = el.innerText.substring(el.innerTextlength, el.innerText.length - 1);
    }, false);
}

const toReadyStateDescription = (state) => {
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
}

const sendNumber = () => {
    var retrieve = document.querySelector('.turing__button-call');
    retrieve.addEventListener('click', (e) => {
        var el = document.querySelector('.turing__calling-number').innerText,
        url = `http://localhost:8080/maquina_turing/call?numbers=${el}`;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = () => {
            if(toReadyStateDescription(xhr.readyState) == 'DONE'){
                if (xhr.status == 200){
                    const json = JSON.parse(xhr.responseText)
                    const calling =  document.querySelector('.turing__calling-status');
                    calling.innerText = json.status;
                    if(json.status == "Ligação válida")
                        calling.innerText = `${ calling.innerText} - ${json.pais} para ${json.regiao}.`;
                }
            }
        };
        xhr.open('GET', url, true);
        xhr.send();
    });
}