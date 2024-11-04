window.onload = function() {
    let drawer = new Drawer();
    drawer.redrawAll(document.querySelector("#R-input").checked);
    console.log("draw graph");

    document.getElementById("R-input").onchange = function() {
        console.log("R changed");
        drawer.redrawAll(document.querySelector('input[name="R-radio-group"]:checked').value);
    }

    let textArea = document.querySelector("input[type=text]");
    textArea.addEventListener("input", validateX);
    textArea.addEventListener("focus", validateX);

    buttons = document.querySelectorAll('input[type=button]');
    for (let button of buttons) {
        button.addEventListener("click", () => {
            for (let el of buttons) {
                if (el != button) {
                    el.isEnabled = true;
                    el.classList.remove("active");
                } else {
                    el.isEnabled = false;
                    el.classList.add("active");
                }
            }
        })
    }

    document.getElementById('checkButton').onclick = function() {
        if (validateX() && validateR() && validateY()) {
            let x = document.getElementById("X-input").value.replace(',', '.');
            let y;
            document.querySelectorAll('input[type=button]').forEach(element => {
                if (element.classList.contains('active')) y = element.value;
            });
            let r = document.querySelector('input[name="R-radio-group"]:checked').value;
            console.log(x, y, r);
            const data = { x, y, r };
            drawer.drawPoint(x, y, true);
            drawer.pushPoint(x, y, true);
        }
    }
}