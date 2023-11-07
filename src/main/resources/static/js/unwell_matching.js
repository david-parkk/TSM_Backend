// Get all the button elements by their class name
var buttons = document.querySelectorAll('.request-area');

// Loop through the button elements
for (var i = 0; i < buttons.length; i++) {
  // Add an event listener to each button element
  buttons[i].addEventListener('click', function(event) {
    // Prevent the default behavior of the button element
    event.preventDefault();
    // Get the parent element of the button element
    var parent = event.target.parentElement;
    // While the parent element is not a form element
    while (parent.tagName !== 'FORM') {
      // Get the parent element of the parent element
      parent = parent.parentElement;
    }
    // Submit the parent form element
console.log(parent);
    parent.submit();

    parent.style.display="none";
    alert("Matching Accepted");
    location.href="unwell_matching.html";
  });
}
