window.onload = function () {

    var arr = window.location.href.split('?')
    console.log(arr);
    if(arr[1] == 'id=true'){
        var billno = generateBillNo()
        document.getElementById("bill-no").innerHTML = billno;
        localStorage['bill-no'] = billno;
        document.getElementById('sender-name').innerHTML = localStorage['sender-name'];
        document.getElementById('sender-mobile').innerHTML = localStorage['sender-mobile'];
        document.getElementById('email').innerHTML = localStorage['email'];
        document.getElementById('booking-no').innerHTML = localStorage['booking-no'];
        document.getElementById('booking-cost').innerHTML = localStorage['booking-cost'];
    }    
}


function generateBillNo() {
    const characters = '0123456789';
    let billno = 'Bill_';
    for (let i = 0; i < 4; i++) {
        billno += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return billno;
}

function printPage(){
    window.print();
}
function goHome(){
    window.location.href = "/user/Home/index.html";
    window.history.pushState(null,null,'/user/Home/index.html')
}