
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

function generateBooking() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let bookno = 'BN_';
    for (let i = 0; i < 5; i++) {
        bookno += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return bookno;
}

var pickupdate = document.getElementById('pickup-date');
var dropoffdate = document.getElementById('drop-date');
const currDate = new Date().toISOString().split('T')[0];
pickupdate.setAttribute('min',currDate);
dropoffdate.disabled = true;
pickupdate.addEventListener('change', function() {
    if (pickupdate.value) {

        dropoffdate.disabled = false;
		let selectedDate = new Date(pickupdate.value)
		selectedDate.setDate(selectedDate.getDate()+2)
		let minDate = selectedDate.toISOString().split('T')[0];
        dropoffdate.setAttribute('min', minDate);

        if (dropoffdate.value && dropoffdate.value < pickupdate.value) {
            dropoffdate.value = '';
        }
    } else {
        dropoffdate.disabled = true;
        dropoffdate.value = ''; 
    }
});

function validateForm() {
    event.preventDefault()
    const senderName = document.getElementById('sendername').value.trim();
    const senderAddress = document.getElementById('senderaddress').value.trim();
    const senderEmail = document.getElementById('senderemail').value.trim();
    const senderMobile = document.getElementById('sendermobile').value.trim();
    const recName = document.getElementById('recname').value.trim();
    const recAddress = document.getElementById('recaddress').value.trim();
    const recEmail = document.getElementById('recemail').value.trim();
    const recMobile = document.getElementById('recmobile').value.trim();
    const weight = document.getElementById('weight').value.trim();
    const date = document.getElementById('date').value;
    const ddate = document.getElementById('drop-date').value;

    // Basic validation
    if (!senderName || !senderAddress || !senderEmail || !senderMobile || !recName || !recAddress || !recEmail || !recMobile || !weight) {
        alert('Please fill in all the required fields.');
        return false;
    }

    // Email validation
    if (!validateEmail(senderEmail) || !validateEmail(recEmail)) {
        alert('Please enter valid email addresses.');
        return false;
    }

    // Mobile number validation (10 digits)
    if (!validateMobile(senderMobile) || !validateMobile(recMobile)) {
        alert('Please enter valid mobile numbers (10 digits).');
        return false;
    }

    // Date validation
    const pickupDate = new Date(date);
    const currentDate = new Date();
    const dropDate = new Date(ddate);
    if (pickupDate <= currentDate) {
        alert('Please select a valid future date for pickup.');
        return false;
    }
    if(dropDate <= currentDate){
        alert('Please select a valid future date for drop.');
        return false;
    }

    // If all validations pass, calculate cost
    calculateCost();
    var bookingNo = generateBooking();
    localStorage['booking-no'] = bookingNo;
    localStorage['email'] = senderEmail;
    localStorage['sender-mobile'] = senderMobile;
    localStorage['dest-address'] = recAddress;
    localStorage['sender-name'] = senderName; 
    window.location.href = "/Payment/index.html";
    alert('Booking successful! Your booking number is ' + bookingNo);
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

    // Display the calculated cost
    document.getElementById('costval').value = estimatedCost.toFixed(2);
    localStorage['booking-cost'] = estimatedCost.toFixed(2);
}

// Hook the form submission to the validation function
function bookParcel() {
    return validateForm(); // Prevent form submission if validation fails
}
