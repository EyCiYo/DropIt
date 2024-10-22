function showForm(){
    event.preventDefault();
    var result = document.getElementById('schedule-container');
    result.style.display = 'block';
    result.scrollIntoView({behavior: "smooth",block: "center"});
}

var datepicker = document.getElementById('pickup-time');
const today = new Date().toISOString().split('T')[0];
datepicker.setAttribute('min',today);