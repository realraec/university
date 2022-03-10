// Spinner over the whole page while the AJAX call is loading
$('#loading-placeholder').load('loading.html', function () {
    $(this).children(':first').unwrap();

    $loadingDiv = $('#loading')

    $(document)
        .ajaxStart(showLoadingDiv)
        .ajaxStop(hideLoadingDiv);
});

function showLoadingDiv() {
    $loadingDiv.show();
}

function hideLoadingDiv() {
    $loadingDiv.hide();
}