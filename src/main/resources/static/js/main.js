$(document).ready(function() {
    $('#fxtable tr').click(function() {
        $('#currencyHistory').attr("src", "http://localhost:8080/getcurrencyfxrates?ccy=" + $(this).find("td")[0].textContent);
    });
    });