$(document).ready(checkDates);
$('#submit').click(function () {
    let dateFrom = $('#dateFrom').val();
    let dateTo = $('#dateTo').val();
    let currency = $('#currency').text();
    window.location.href="http://localhost:8080/getcurrencyfxrates?ccy=" + currency + "&dtFrom=" + dateFrom + "&dtTo=" + dateTo;
});

function checkDates(){
    if($('#dateFrom').val() === "") {
        var now = new Date();
        var month = (now.getMonth() + 1);
        var day = now.getDate();
        if (month < 10)
            month = "0" + month;
        if (day < 10)
            day = "0" + day;
        var monthBefore = now.getFullYear() + '-' + "0" + now.getMonth() + '-' + day;
        var today = now.getFullYear() + '-' + month + '-' + day;
        $('#dateFrom').val(monthBefore);
        $('#dateTo').val(today);
    }
}

function calculateChange(){

}

