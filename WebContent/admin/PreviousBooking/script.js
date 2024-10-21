
function getHistory() {
    event.preventDefault();
    var table = document.getElementById("previous-details");
    table.style.display = "block";
    table.scrollIntoView({ behavior: "smooth", block: "center" });
}

const fromDate = document.getElementById("fromdate");
const toDate = document.getElementById("todate");

// Disable the "To" date initially
toDate.disabled = true;

// Add event listener to the "From" date selector
fromDate.addEventListener('change', function() {
    if (fromDate.value) {
        // Enable the "To" date selector
        toDate.disabled = false;

        // Set the "min" value for the "To" date as the selected "From" date
        toDate.setAttribute('min', fromDate.value);

        // Clear the "To" date if it is earlier than the new "min" value
        if (toDate.value && toDate.value < fromDate.value) {
            toDate.value = '';
        }
    } else {
        // If no "From" date is selected, disable the "To" date selector
        toDate.disabled = true;
        toDate.value = ''; // Reset "To" date
    }
});

