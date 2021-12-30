function validate() {
    let name = $('#name').val().trim();
    let email = $('#email').val().trim();
    let descrizione = $('#descrizione').val().trim();
    let categoria = $('#categoria').val();
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

    if(descrizione.length < 10 || descrizione.length > 2048) {
        message += "<li>La descrizione deve avere una lunghezza compresa tra i 10 e i 2048 caratteri.</li>";
        valid = false;
    }

    regex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
    if(!regex.test(email)) {
        message += "<li>Email non valida.</li>";
        valid = false;
    }

    if(categoria == null) {
        message += "<li>Inserisci la categoria.</li>";
        valid = false;
    }
    else if(categoria !== "strategia" && categoria !== "sport" && categoria !== "rpg" && categoria !== "simulazione" && categoria !== "action"
        && categoria !== "sparatutto" && categoria !== "picchiaduro" && categoria !== "guida" && categoria !== "casual") {
        message += "<li>Categoria non valida.</li>";
        valid = false;
    }

    if(!valid) {
        message += "</ul>";
        alertBox(message, "danger");
    }
    return valid;
}