function validate() {
    let name = $('#name').val().trim();
    let surname = $('#surname').val().trim();
    let email = $('#email').val().trim();
    let password = $('#password').val();
    let sesso = $('#sesso').val();
    let confirmPassword = $('#confirmPassword').val();
    let username = $('#username').val().trim();
    let dataDiNascita = $('#dataDiNascita').val().split('-');
    let message = "<ul>";
    let valid = true;
    let regex = /^[a-zA-Z ]+$/

    if(!regex.test(name)) {
        message += "<li>Il nome non rispetta il formato adatto.</li>";
        valid = false;
    }
    if(name.length < 2 || name.length > 32) {
        message += "<li>Il nome deve avere una lunghezza compresa tra i 2 e i 32 caratteri.</li>";
        valid = false;
    }

    if(!regex.test(surname)) {
        message += "<li>Il cognome non rispetta il formato adatto.</li>";
        valid = false;
    }
    if(surname.length < 2 || surname.length > 32) {
        message += "<li>Il cognome deve avere una lunghezza compresa tra i 2 e i 32 caratteri.</li>";
        valid = false;
    }

    regex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
    if(!regex.test(email)) {
        message += "<li>Email non valida.</li>";
        valid = false;
    }

    if(password.length < 6 || password.length > 64) {
        message += "<li>La password deve avere una lunghezza compresa tra i 6 e i 64 caratteri.</li>";
        valid = false;
    }
    if(password !== confirmPassword) {
        message += "<li>Le password non corrispondono.</li>";
        valid = false;
    }

    if(username.length < 3 || username.length > 20) {
        message += "<li>L'username deve avere una lunghezza compresa tra i 3 e i 20 caratteri.</li>";
        valid = false;
    }

    let oggi = new Date();
    let date = new Date(dataDiNascita);
    let minDate = new Date('1900-01-01');
    if(date < minDate || date > oggi) {
        message += "<li>Data di nascita non valida.</li>";
        valid = false;
    }

    if(sesso == null) {
        message += "<li>Inserisci il sesso.</li>";
        valid = false;
    }
    else if(sesso !== "maschio" && sesso !== "femmina" && sesso !== "altro") {
        message += "<li>Sesso non valido.</li>";
        valid = false;
    }

    if(!valid) {
        message += "</ul>";
        alertBox(message, "danger");
    }
    return valid;
}