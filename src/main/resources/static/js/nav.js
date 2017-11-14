document.body.onmousewheel = function(event) {
    event = event || window.event;
    console.dir(event);

    if($(document).scrollTop() > 0 ) {

        $(".navbar").css("background-color","#fff");

        $(".nav li a").css("color","black");
    }else {

        $(".navbar").css("background-color","rgba(0,0,0,0)");
        $(".nav li a").css("color","#898989");

    }


};
