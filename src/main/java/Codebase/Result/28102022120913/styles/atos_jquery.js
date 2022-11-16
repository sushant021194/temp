$(document).ready(function(){
  $("tr.heading").css("background-color","Ivory");
  $("tr.heading").css("color","black");
  $("tr.heading").nextUntil(".heading").slideToggle();
  $("tr.heading").click(function(){
    $(this).nextUntil(".heading").slideToggle();
  });
$("tbody").find("td:contains('Fail')").parent().each(function()
{ if($(this).prevUntil(".heading").text()=="")
	$(this).prev("tr.heading").css("color","red");
   else
	$(this).prevUntil(".heading").prev("tr.heading").css("color","red");
});

});