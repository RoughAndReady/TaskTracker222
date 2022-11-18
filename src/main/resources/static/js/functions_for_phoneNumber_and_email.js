//functions for validating  email
const militaryEmail = document.getElementById("militaryMail");
militaryEmail.addEventListener("input", (event) => {
  if (militaryEmail.validity.typeMismatch) {
    militaryEmail.setCustomValidity("I am expecting an e-mail address!");
    militaryEmail.reportValidity();
  } else {
    militaryEmail.setCustomValidity("");
  }
});

const civilianEmail = document.getElementById("civilianMail");
civilianEmail.addEventListener("input", (event) => {
  if (civilianEmail.validity.typeMismatch) {
    civilianEmail.setCustomValidity("I am expecting an e-mail address!");
    civilianEmail.reportValidity();
  } else {
    civilianEmail.setCustomValidity("");
  }
});


//functions for formatting phone number
const formatPersonalNumber= document.getElementById("personal");
formatPersonalNumber.addEventListener("input", (event) => {
  if (isNaN(formatPersonalNumber.value))
{ 
    formatPersonalNumber.setCustomValidity("I am expecting a phone number!");
    formatPersonalNumber.reportValidity();
}
else{
  formatPersonalNumber.setCustomValidity("");
}

var cleaned = ('' + formatPersonalNumber.value).replace(/\D/g, '');
  var match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
  if (match) {
    formatPersonalNumber.value = '(' + match[1] + ') ' + match[2] + '-' + match[3];
    return '(' + match[1] + ') ' + match[2] + '-' + match[3];
  }
  return null;

});

const formatOfficeNumber= document.getElementById("office");
formatOfficeNumber.addEventListener("input", (event) => {
  if (isNaN(formatOfficeNumber.value))
{ 
    formatOfficeNumber.setCustomValidity("I am expecting a phone number!");
    formatOfficeNumber.reportValidity();
}
else{
  formatOfficeNumber.setCustomValidity("");
}

var cleaned = ('' + formatOfficeNumber.value).replace(/\D/g, '');
  var match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
  if (match) {
    formatOfficeNumber.value = '(' + match[1] + ') ' + match[2] + '-' + match[3];
    return '(' + match[1] + ') ' + match[2] + '-' + match[3];
  }
  return null;

});