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