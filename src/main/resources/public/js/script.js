$(document).ready(function(){
  $("#button").submit(function(event){
event.preventDefault();
        $("#shoot").slideDown(100);
        $("#plate").slideDown(100);
        $("#scala").slideDown(100);
        $("#pool").slideDown(100);
        $("#pair").slideDown(1000);
        $("#prey").slideDown(100);
        $("#hint").fadeIn(100);
        $("#pluto").fadeIn(100);
    });

  });