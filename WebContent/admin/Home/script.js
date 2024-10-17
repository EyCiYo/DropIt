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
document.getElementById("services-btn").addEventListener("mouseenter",(event)=>{
    var servicesDropdown = document.getElementById("services-dropdown")
    servicesDropdown.style.display = "block";
    document.getElementById("services-chevron").classList.add('chevron-rotate');
})

document.getElementById("services-btn").addEventListener("mouseleave",(event)=>{
    var servicesDropdown = document.getElementById("services-dropdown")
    servicesDropdown.style.display = "none";
    document.getElementById("services-chevron").classList.remove('chevron-rotate');
})

const numberFieldSender = document.getElementById('mobile-no');

numberFieldSender.addEventListener('input', function() {
    const value = numberFieldSender.value.toString();
    if (value.length > 10) {
        numberFieldSender.value = value.substring(0, 10);
    }
});

function trackParcel() {
    event.preventDefault();
    if(document.getElementById('mobile-no').value.length < 10){
        alert("Please enter a valid mobile number");
        return false;
    }
    localStorage['tracking-id'] = document.getElementById('tracking-id').value;
    console.log(localStorage['tracking-id']);
    document.location.href = 'http://127.0.0.1:5500/user/Tracking/index.html?id='+ encodeURIComponent(true);
    return true;
}
