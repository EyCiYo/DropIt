
function getHistory(){
    event.preventDefault();
    
    var email = document.getElementById("email").value;

    if(email === "john@gmail.com"){
        var table = document.getElementById("previous-details");
        table.style.display = "block";
        table.scrollIntoView({behavior: "smooth", block: "center"});
        return true;
    }
    else{
        alert("No such user found.");
        return false;
    }
}

