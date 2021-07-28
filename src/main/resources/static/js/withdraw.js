function isNumber(evt) {

    var keyCharCode = (evt.which) ? evt.which : evt.keyCode

    if (keyCharCode != 46 && keyCharCode > 31 && (keyCharCode < 48 || keyCharCode > 57))
        return false;

    return true;
}
