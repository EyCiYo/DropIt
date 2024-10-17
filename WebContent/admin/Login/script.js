
function validateLogin(){
    event.preventDefault();
    var uid = document.getElementById('email').value;
    var psw = document.getElementById('password').value.toString();
    if(uid === "admin@gmail.com"){
        if(psw === "admin")
        {
            window.location.href = "/admin/Home/index.html"    
            return true;
        }
        else{alert("Please enter the correct password.");return false;}
        
    }
    else{
        alert("Please enter correct Username.");
        return false;
    }
    

}