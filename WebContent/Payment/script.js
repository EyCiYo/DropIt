let selectedMethod = 'credit'; // Default selection

var cardNumber = document.getElementById("cardNumber");
var cvv = document.getElementById("cvv");
cardNumber.addEventListener('input', function() {
    cardNumber.value = cardNumber.value.replace(/[^0-9]/g, '');
    let formatedValue = "";
    if(cardNumber.value.length % 4 == 0) {
        formatedValue = cardNumber.value +'-';
        cardNumber.value = formatedValue;
    }
    if (cardNumber.value.length > 19) {
        cardNumber.value = cardNumber.value.substring(0, 19);
    }
});

cvv.addEventListener('input', function() {
    cvv.value = cvv.value.replace(/[^0-9]/g, '');
    if (cvv.value.length > 3) {
        cvv.value = cvv.value.substring(0, 3);
    }
});
window.onload = function() {
    if (localStorage['booking-no'] === undefined || localStorage['booking-cost'] === undefined) {
        document.getElementById('book-id').innerHTML = "BN_as34W2";
        document.getElementById('amount').innerHTML = "Rs. 1000";
    }
    else{
        document.getElementById('book-id').innerHTML = localStorage['booking-no'].toString();
        document.getElementById('amount').innerHTML = "Rs. "+ localStorage['booking-cost'].toString();
    }
    
}

function showPaymentForm() {
    selectedMethod = document.querySelector('input[name="payment-method"]:checked').value; // Store the selected payment method
    const formTitle = document.getElementById('formTitle');
    
    // Change title based on selected method
    if (selectedMethod === 'credit') {
        formTitle.textContent = 'Credit Card Payment';
    } else {
        formTitle.textContent = 'Debit Card Payment';
    }
    const cardFormContainer = document.getElementById('cardFormContainer');
    cardFormContainer.classList.add('show'); // Show the payment form
}

function closeForm() {
    const cardFormContainer = document.getElementById('cardFormContainer');
    cardFormContainer.classList.remove('show'); // Hide the payment form
}

function submitPayment() {
    event.preventDefault()
    const cardNumber = document.getElementById('cardNumber').value;
    const cardName = document.getElementById('cardName').value;
    const expiryDate = document.getElementById('expiryDate').value;
    const cvv = document.getElementById('cvv').value;

    const today =new Date();
    const exp = new Date(expiryDate);

    if(exp < today){
        alert("Your card has expired.");
        return false;
    }

    // Validate inputs (basic example)
    if (cardNumber && cardName && expiryDate && cvv) {
        alert('Payment submitted successfully!');
        window.location.href = "/Bill/index.html?id=" + encodeURIComponent(true);
        closeForm(); // Close form after submission
    } else {
        alert('Please fill in all fields.');
    }
}

function goHome(){
    window.location.href = "/user/Home/index.html";
}

