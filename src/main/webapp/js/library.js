$(document).ready(function() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })

    // bottone per tornare all'inizio della pagina
    const mybutton = document.getElementById("backtotop");
    window.onscroll = function() {scrollFunction()};
    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }
});

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

// Errori validator
function alertBox(message, type, id) {
    if(id === undefined || id === null) {
        id = '#liveAlertPlaceholder';
    }
    let alertPlaceholder = $(id);
    alertPlaceholder.hide();
    alertPlaceholder.empty();
    let wrapper = document.createElement('div');
    wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
    alertPlaceholder.append(wrapper);
    alertPlaceholder.show(300);
}
