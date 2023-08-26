const hours = document.querySelectorAll('.hours');
const minutes = document.querySelectorAll('.minutes');
const seconds = document.querySelectorAll('.seconds');

setInterval(() => {
    const now = new Date();
    h = now.getHours(),
        m = now.getMinutes(),
        s = now.getSeconds();
    if (h < 10) h = '0' + h;
    if (m < 10) m = '0' + m;
    if (s < 10) s = '0' + s;
    updateDigits('hours', h);
    updateDigits('minutes', m);
    updateDigits('seconds', s);
}, 1000);

const updateDigits = (baseClass, digits) => {
    switch (baseClass) {
        case 'hours': {
            const numbers = `${digits}`.split('');
            numbers.forEach((number, index) => {
                hours[index].setAttribute('class', `digit ${baseClass} d-${number}`);
            });
        }
        case 'minutes': {
            const numbers = `${digits}`.split('');
            numbers.forEach((number, index) => {
                minutes[index].setAttribute('class', `digit ${baseClass} d-${number}`);
            });
        }

        case 'seconds': {
            const numbers = `${digits}`.split('');
            numbers.forEach((number, index) => {
                seconds[index].setAttribute('class', `digit ${baseClass} d-${number}`);
            });
        }
    }
};