function hover(element) {
    //element.setAttribute('src', '${context}/icons/ylogo.png');
}
function unhover(element) {
    //element.setAttribute('src', '${context}/icons/logo.png');
}

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
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}