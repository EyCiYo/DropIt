let selectedMethod = 'credit'; // Default selection

var cardNumber = document.getElementsByClassName("num-inp");
var cvv = document.getElementById("cvv");
// Convert HTMLCollection to an array using Array.from()
Array.from(cardNumber).forEach((numInput) => {
    numInput.addEventListener('input', function () {
        // Remove all non-digit characters
        let inputValue = numInput.value.replace(/[^0-9]/g, '');

        // Set the formatted value back to the input field
        numInput.value = inputValue;
    });
});


cvv.addEventListener('input', function() {
    cvv.value = cvv.value.replace(/[^0-9]/g, '');
    /*if (cvv.value.length > 3) {
        cvv.value = cvv.value.substring(0, 3);
    }*/
});

function updateString(value) {
    selectedMethod = value;
    const formTitle = document.getElementById('formTitle');
    if (selectedMethod === 'credit') {
        formTitle.textContent = 'Credit Card Payment';
    } else {
        formTitle.textContent = 'Debit Card Payment';
    }
}

function showPaymentForm() {
    selectedMethod = document.querySelector('input[name="payment-method"]:checked').value; // Store the selected payment method
    // Change title based on selected method
    updateString(selectedMethod);

    const cardFormContainer = document.getElementById('cardFormContainer');
    cardFormContainer.classList.add('show'); // Show the payment form
}

function resetForm() {
    const cardFormContainer = document.getElementById('cardFormContainer');
    cardFormContainer.reset();
}



