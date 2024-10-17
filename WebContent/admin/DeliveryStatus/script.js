var statusMap = {
    "Booking Placed" : "booking-placed",
    "Picked Up":"picked-up",
    "In Transit":"in-transit",
    "Out for Delivery":"delivery",
    "Delivered":"delivered",
    "Returnerd":"returned"
}

function getKeyByValue(object, value) {
    for (let prop in object) {
        if (object.hasOwnProperty(prop)) {
            if (object[prop] === value)
                return prop;
        }
    }
}
function getParcel(){
    event.preventDefault(); 
    var parcelId = document.forms["update-form"]["tracking-id"].value;
    console.log("inside trackParcel: ",parcelId);
    if(parcelId == ""){
        alert("Please enter a tracking ID");
    }
    else{
        var trackingResult = document.getElementById("tracking-result");
        trackingResult.style.display = "block";
        trackingResult.scrollIntoView({behavior: "smooth",block: "center"});
        document.getElementById("tracking-id-result").innerHTML = parcelId.toString();
        document.getElementById("status-result").innerHTML = "In Transit"
        document.getElementById("status").value = statusMap["In Transit"]
    }
}

function updateParcel(){
    event.preventDefault();
    var newStatus = document.getElementById("status").value;
    document.getElementById("status-result").innerHTML = getKeyByValue(statusMap,newStatus);
    alert("Parcel Status Updated Successfully!")
}