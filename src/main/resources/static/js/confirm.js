var amount;
var totalDispense = 0;

function loadInstruction() {
    let displayMessage;

    if (amount == totalDispense) {
        displayMessage = "Confirm withdrawal of " + amount + "?";
    } else {
        displayMessage = "Amount not available, would you like to draw " + totalDispense + "?";
    }
    document.getElementById("instruction").innerHTML = displayMessage;
}
