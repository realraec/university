/* $(function(){
  $("#nav-placeholder").load("navbar.html");
}); */

$('#nav-placeholder').load('navbar.html', function () {
  $(this).children(':first').unwrap();

  //let navbar = $('#navbar');

  /* $("#user-fullname").text(localStorage["userFullname"]);
  $("#user-fullname-below-large").text(localStorage["userFullname"]);

  $("#user-type").text(localStorage["userType"]);
  $("#user-type-below-large").text(localStorage["userType"]); */




  // Once the DOM is properly set up
  $(document).ready(function () {
    
    let links = $('#navbar').find("a[class='nav-link']");
    let buttonLinks = $('#navbar').find("button[onclick]");
    links.push(...buttonLinks);

    let documentURL = document.URL;

    for (let i = 0; i < links.length; i++) {

      let targetAttribute = links[i].getAttribute('href');

      if (targetAttribute == null) {
        targetAttribute = links[i].getAttribute('onclick');
      }

      if (documentURL.endsWith("lookup_students.html") && targetAttribute == "lookup_students.html") {
        links[i].classList.add('active');
        return;
      } else if (documentURL.endsWith("lookup_professors.html") && targetAttribute == "lookup_professors.html") {
        links[i].classList.add('active');
        return;
      } else if (documentURL.endsWith("lookup_courses.html") && targetAttribute == "lookup_courses.html") {
        links[i].classList.add('active');
        return;
      } else if (documentURL.endsWith("lookup_degrees.html") && targetAttribute == "lookup_degrees.html") {
        links[i].classList.add('active');
        return;
      } else if (documentURL.endsWith("account.html") && targetAttribute.includes("account.html")) {
        links[i].classList.add('active');
        return;
      } else if (documentURL.endsWith("settings.html") && targetAttribute.includes("settings.html")) {
        links[i].classList.add('active');
        return;
      }

    }


  });
});
