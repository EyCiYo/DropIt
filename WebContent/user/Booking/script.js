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

const numberFieldSender = document.getElementById('sendermobile');
const numberFieldReceiver = document.getElementById('recmobile');

numberFieldSender.addEventListener('input', function() {
    const value = numberFieldSender.value.toString();
    if (value.length > 10) {
        numberFieldSender.value = value.substring(0, 10);
    }
});
numberFieldReceiver.addEventListener('input', function() {
    const value = numberFieldReceiver.value.toString();
    if (value.length > 10) {
        numberFieldReceiver.value = value.substring(0, 10);
    }
});

// Email validation function
function validateEmail(email) {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}

// Mobile number validation function
function validateMobile(mobile) {
    const mobilePattern = /^\d{10}$/;
    if(mobilePattern.test(mobile) && mobile.length == 10){return true;}
    return false;
}

function generateBooking() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let bookno = 'BN_';
    for (let i = 0; i < 9; i++) {
        bookno += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return bookno;
}

function validateForm() {
    event.preventDefault();
    const senderName = document.getElementById('sendername').value.trim();
    const senderAddress = document.getElementById('senderaddress').value.trim();
    const senderEmail = document.getElementById('senderemail').value.trim();
    const senderMobile = document.getElementById('sendermobile').value.trim();
    const recName = document.getElementById('recname').value.trim();
    const recAddress = document.getElementById('recaddress').value.trim();
    const recEmail = document.getElementById('recemail').value.trim();
    const recMobile = document.getElementById('recmobile').value.trim();
    const weight = document.getElementById('weight').value.trim();
    const date = document.getElementById('pickup-date').value;
    const ddate = document.getElementById('drop-date').value;
    console.log("aaaa"+senderMobile);
    // Basic validation
    if (!senderName || !senderAddress || !senderEmail || !senderMobile || !recName || !recAddress || !recEmail || !recMobile || !weight) {
        alert('Please fill in all the required fields.');
        return false;
    }
    console.log("name ok");
    // Email validation
    if (!validateEmail(senderEmail) || !validateEmail(recEmail)) {
        alert('Please enter valid email addresses.');
        return false;
    }
    console.log("email ok");
    // Mobile number validation (10 digits)
    if (!validateMobile(senderMobile) || !validateMobile(recMobile)) {
        alert('Please enter valid mobile numbers (10 digits).');
        return false;
    }
    console.log("mobile ok");
    // Date validation
    const pickupDate = new Date(date);
    const dropDate = new Date(ddate);
    const currentDate = new Date();
    if (pickupDate <= currentDate) {
        alert('Please select a valid future date for pickup.');
        return false;
    }
    if(dropDate <= currentDate){
        alert('Please select a valid future date for drop.');
        return false;
    }
    console.log("date ok");

    // If all validations pass, calculate cost
    calculateCost();
    var bookingNo = generateBooking();
    localStorage['booking-no'] = bookingNo;
    localStorage['email'] = senderEmail;
    localStorage['sender-mobile'] = senderMobile;
    localStorage['dest-address'] = recAddress;
    localStorage['sender-name'] = senderName; 
    alert('Booking successful! Your booking number is ' + bookingNo);
    window.location.href = "/../../Payment/index.html";
    return true;
}



// Cost calculation function based on dimensions and weight
function calculateCost() {
    const length = parseFloat(document.getElementById('length').value) || 0;
    const breadth = parseFloat(document.getElementById('breadth').value) || 0;
    const height = parseFloat(document.getElementById('height').value) || 0;
    const weight = parseFloat(document.getElementById('weight').value) || 0;
    const speed =  document.querySelector('input[name="shippingoption"]:checked').value;
    const packageType = document.querySelector('input[name="packagingtype"]:checked').value;

    if(!length || !breadth||!height||!weight){
        alert("Please enter the dimensions to calculate the cost!");
        return;
    }
    // Calculate volume in cubic cm
    const volume = length * breadth * height;
    const costSpeed = {
        "standard": 1,
        "express": 1.5,
        "bussiness": 2
    }
    const costPackage = {
        "standard": 10,
        "eco": 25,
        "custom": 40,
        "fragile": 50
    }
    // Basic cost factors (these can be adjusted as needed)
    const costPerKg = 50; // base cost per kg
    const costPerCubicCm = 0.02; // base cost per cubic cm
    // Total cost calculation
    const estimatedCost = ((weight * costPerKg) + (volume * costPerCubicCm))*costSpeed[speed] + costPackage[packageType];
    console.log(estimatedCost);
    // Display the calculated cost
    document.getElementById('costval').value = estimatedCost.toFixed(2);
    localStorage['booking-cost'] = estimatedCost.toFixed(2);
}

// Hook the form submission to the validation function
function bookParcel() {
    return validateForm(); // Prevent form submission if validation fails
}
