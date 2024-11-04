function validateX() {
    el = document.getElementById("X-input");
    x = el.value.replace(',', '.');
    if (isNaN(parseFloat(x)) || !isFinite(x) || parseFloat(x) > 5 || parseFloat(x) < -3) {
        el.setCustomValidity("Ожидается число от -3 до 5");
        el.reportValidity();
        console.log("x invalid")
        return false;
    } else {
        el.setCustomValidity("");
        el.reportValidity();
        console.log("x valid");
        return true;
    }
}

function validateR() {
    radios = document.querySelectorAll('input[type=radio]');
    for (let radio of radios) {
        if (radio.checked) {
            console.log("r valid")
            return true;
        }
    }
    console.log("r invalid")
    return false;
}

function validateY() {
    buttons = document.querySelectorAll('input[type=button]');
    for (let button of buttons) {
        if (button.classList.contains('active')) {
            console.log("y valid")
            return true;
        }
    }
    console.log("y invalid")
    return false;
}