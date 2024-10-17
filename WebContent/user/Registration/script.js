document.getElementById("login-btn").addEventListener("mouseenter",(event)=>{
    var loginDropdown = document.getElementById("login-dropdown")
    loginDropdown.style.display = "block";
    document.getElementById("login-chevron").classList.add('chevron-rotate');
})

document.getElementById("login-btn").addEventListener("mouseleave",(event)=>{
    var loginDropdown = document.getElementById("login-dropdown")
    loginDropdown.style.display = "none";
    document.getElementById("login-chevron").classList.remove('chevron-rotate');
})
document.getElementById("support-btn").addEventListener("mouseenter",(event)=>{
    var supportDropdown = document.getElementById("support-dropdown")
    supportDropdown.style.display = "block";
    document.getElementById("support-chevron").classList.add('chevron-rotate');
})

document.getElementById("support-btn").addEventListener("mouseleave",(event)=>{
    var supportDropdown = document.getElementById("support-dropdown")
    supportDropdown.style.display = "none";
    document.getElementById("support-chevron").classList.remove('chevron-rotate');
})

document.getElementById("services-btn").addEventListener("mouseenter",(event)=>{
    var servicesDropdown = document.getElementById("services-dropdown")
    servicesDropdown.style.display = "block";
    document.getElementById("services-chevron").classList.add('chevron-rotate');
})

document.getElementById("services-btn").addEventListener("mouseleave",(event)=>{
    var servicesDropdown = document.getElementById("services-dropdown")
    servicesDropdown.style.display = "none";
    document.getElementById("services-chevron").classList.remove('chevron-rotate');
})
var numberFieldSender = document.getElementById("phone");
numberFieldSender.addEventListener('input', function() {
    const value = numberFieldSender.value.toString();
    if (value.length > 10) {
        numberFieldSender.value = value.substring(0, 10);
    }
});
function resetForm(){
    var form = document.getElementById("registrationForm");
    var preservedField = document.getElementById("generated_username");
    var fieldValue = preservedField.value;

    form.reset();

    preservedField.value = fieldValue; 
}

function generateUsername() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let username = 'User_';
    for (let i = 0; i < 8; i++) {
        username += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return username;
}

// Set generated username when the page loads
document.addEventListener('DOMContentLoaded', () => {
    const usernameField = document.getElementById('generated_username');
    usernameField.value = generateUsername();
});

function validateForm(event) {
    event.preventDefault();
    const name = document.getElementById('full_name').value.trim();
    const address = document.getElementById('address').value.trim();
    const email = document.getElementById('email').value.trim();
    const mobile = document.getElementById('phone').value.trim();
    const password = document.getElementById('password').value.trim();
    const cpassword = document.getElementById('confirm_password').value.trim();
    const uname = document.getElementById('generated_username').value.trim();
   	const checkboxes = document.getElementsByName('preferences');
   	const preferences = []
   	Array.from(checkboxes).filter(checkbox => {
		   if(checkbox.checked){
			   preferences.push(checkbox.value)
		   }
	   });
	
	console.log(preferences)

    // Basic validation
    if (!name || !address || !email || !mobile || !password || !cpassword) {
        alert('Please fill in all the required fields.');
        return false;
    }
    if(name.length > 30 ){alert("Maximum length of name allowed is 30.");return false;}
    else if(name.length < 5){alert("Minimum length of name allowed is 5.");return false;}
    // Email validation
    if (!validateEmail(email)) {
        alert('Please enter valid email addresses.');
        return false;
    }

    //Password check
    if(!pswCheck(password)){alert("Please follow password creation criteria.");return false;}
    else if(password != cpassword){alert("Password and Confirm password needs to be the same."); return false;}


    // Mobile number validation (10 digits)
    if (!validateMobile(mobile)) {
        alert('Please enter valid mobile numbers (10 digits).');
        return false;
    }
    alert("Everything all right");
    document.forms['registrationForm'].submit();
    return true;
}

// Email validation function
function validateEmail(email) {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}

// Mobile number validation function
function validateMobile(mobile) {
    const mobilePattern = /^\d{10}$/;
    return mobilePattern.test(mobile);
}
function pswCheck(psw){
    var upperCaseLetters = /[A-Z]/g;
    var lowerCaseLetters = /[a-z]/g;
    var numbers = /[0-9]/g;
    
    if(psw.length < 8 || psw.length >16)
    {
        return false
    }
    else if(!psw.match(numbers))
    {
        return false
    }
    else if(!psw.match(upperCaseLetters))
    {
        return false
    }
    else if(!psw.match(lowerCaseLetters))
    {
        return false
    }
    else{
        return true
    }
}