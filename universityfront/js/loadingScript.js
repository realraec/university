// Spinner over the whole page while the AJAX call is loading
$('#loading-placeholder').load('loading.html', function () {
    $(this).children(':first').unwrap();

    let $loadingDiv = $('#loading')

    $(document)
        .ajaxStart(function () {
            $loadingDiv.show();
        }).ajaxStop(function () {
            $loadingDiv.hide();
        });
});