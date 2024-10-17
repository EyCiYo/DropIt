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


function trackParcel() {
    event.preventDefault();
    localStorage['tracking-id'] = document.getElementById('tracking-id').value;
    console.log(localStorage['tracking-id']);
    document.location.href = 'http://127.0.0.1:5500/user/Tracking/index.html?id='+ encodeURIComponent(true);
    return true;
}
