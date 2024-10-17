function trackParcel(){
    event.preventDefault(); 
    var parcelId = document.forms["track-form"]["tracking-id"].value;
    console.log("inside trackParcel: ",parcelId);
    if(parcelId == ""){
        alert("Please enter a tracking ID");
    }
    else{
        var trackingResult = document.getElementById("tracking-result");
        trackingResult.style.display = "block";
        trackingResult.scrollIntoView({behavior: "smooth",block: "center"});
        document.getElementById("tracking-id").innerHTML = parcelId.toString();
        document.getElementById("user-id").innerHTML = "User_1sW21";
        document.getElementById("current-location").innerHTML = "Bangalore";
        document.getElementById("estimated-arrival").innerHTML = "10th May 2021";
        startProgressBar(4);
    }
}

function startProgressBar(progress) {
    // Get all circles and bars
    const circles = document.querySelectorAll('.circle');
    const bars = document.querySelectorAll('.bar');

    // Loop through and fill only up to the specified progress (number of circles)
    circles.forEach((circle, index) => {
        if (index < progress) {
            setTimeout(() => {
                if (bars[index] && index < progress-1) {
                    bars[index].classList.add('progressed');
                }
                circle.classList.add('progressed');
            }, index * 200); // Delay of 500ms between each progress step
        }
    });
}

function showAll() {
    event.preventDefault();
    var prevTable = document.getElementById("previous-details");
    prevTable.style.display = "block";
    prevTable.scrollIntoView({behavior:"smooth",block:"center"})

}



