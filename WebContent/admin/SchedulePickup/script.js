function getParcel(){
    event.preventDefault(); 
    var parcelId = document.forms["schedule-form"]["track-id"].value;
    console.log("inside trackParcel: ",parcelId);
    if(parcelId == ""){
        alert("Please enter a tracking ID");
    }
    else{
        var trackingResult = document.getElementById("schedule-container");
        trackingResult.style.display = "block";
        trackingResult.scrollIntoView({behavior: "smooth",block: "center"});
        document.getElementById("tracking-id-result").innerHTML = parcelId.toString();
        document.getElementById("preference-result").innerHTML = "27/09/2024, 12:36:00PM"
    }
}

function scheduleParcel(){
    event.preventDefault();
    var date = document.getElementById("pickup-time").value;
    var newDate = new Date(date).toLocaleString();
    if(newDate == ""){
        alert("Please enter a date");
        return;
    }
    document.getElementById("title").innerHTML = "Scheduled Pickup Time: ";
    var newStatus = document.getElementById("preference-result");
   newStatus.innerHTML = newDate;
    alert("Parcel Scheduled Successfully!")
}