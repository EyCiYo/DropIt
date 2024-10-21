
function startProgressBar(value) {
    var progress = parseInt(value)
    var trackingResult = document.getElementById("tracking-result");
    trackingResult.style.display = "block";
    trackingResult.scrollIntoView({behavior: "smooth",block: "center"});
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



