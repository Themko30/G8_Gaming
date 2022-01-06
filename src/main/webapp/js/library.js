$(document).ready(function() {
    // tooltips
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

    // barra di ricerca
    $('#cercaInput').keyup(function(e) {
        let query = $(this).val().trim();
        if(query !== '') { // Se non è vuoto
            $('#s-res').show();
            if (e.keyCode === 13) { // Se viene premuto il tasto Invio
                $('#cercaBtn').trigger('click'); // Invia la ricerca
            }

            $.ajax({
                url: '/G8_Gaming_war_exploded/Prodotto/Ricerca/api',
                method: 'GET',
                data: {nome: query},
                success: function(data) {
                    let results = $('#results');
                    results.empty();
                    for(let index in data.products) {
                        results.append('<li><a href="/G8_Gaming_war_exploded/Prodotto/Visualizza?prodotto='+data.products[index].codice+'">'+data.products[index].nome+'</a></li>');
                    }
                    results.show();
                },
                error: function(){
                    let results = $('#results');
                    results.empty();
                    results.append("Nessun prodotto trovato");
                    results.show();
                }
            })
        } else {
            $('#s-res').hide();
        }
    });
});

function cerca() {
    let query = $('#cercaInput').val().trim();
    return query !== undefined && query !== null && query !== '';
}

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
