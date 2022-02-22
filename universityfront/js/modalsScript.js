$('#modals-placeholder').load('modals.html', function () {
    $(this).children(':first').unwrap();


    // Global constants
    const codeModalConfirmButton = document.getElementById('codeModalConfirmButton');
    const codeModalCancelButton = document.getElementById('codeModalCancelButton');
    const codeModalCloseButton = document.getElementById('codeModalCloseButton');
    const codeModalSpinner = document.getElementById('codeModalSpinner');
    const codeModalInput = document.getElementById('codeModalInput');
    const codeModalInputLabel = document.getElementById('codeModalInputLabel');

    const radioModalConfirmButton = document.getElementById('radioModalConfirmButton');
    const radioModalInputLabel = document.getElementById('radioModalInputLabel');
    const radioModalInputRadio1Label = document.getElementById('radioModalInputRadio1Label');
    const radioModalInputRadio2Label = document.getElementById('radioModalInputRadio2Label');
    const radioModalCancelButton = document.getElementById('radioModalCancelButton');
    const radioModalCloseButton = document.getElementById('radioModalCloseButton');
    const radioModalSpinner = document.getElementById('radioModalSpinner');

    const selectModalConfirmButton = document.getElementById('selectModalConfirmButton');
    const selectModalCancelButton = document.getElementById('selectModalCancelButton');
    const selectModalCloseButton = document.getElementById('selectModalCloseButton');
    const selectModalSpinner = document.getElementById('selectModalSpinner');
    const selectModalInput = document.getElementById('selectModalInput');
    const selectModalInputOption = document.getElementById('selectModalInputOption');
});


function disableButtonsInModal() {
    codeModalConfirmButton.setAttribute("disabled", "true");
    codeModalCancelButton.setAttribute("disabled", "true");
    codeModalCloseButton.setAttribute("disabled", "true");
    codeModalSpinner.removeAttribute("hidden");

    radioModalConfirmButton.setAttribute("disabled", "true");
    radioModalCancelButton.setAttribute("disabled", "true");
    radioModalCloseButton.setAttribute("disabled", "true");
    radioModalSpinner.removeAttribute("hidden");

    selectModalConfirmButton.setAttribute("disabled", "true");
    selectModalCancelButton.setAttribute("disabled", "true");
    selectModalCloseButton.setAttribute("disabled", "true");
    selectModalSpinner.removeAttribute("hidden");
}

function enableButtonsInModal() {
    codeModalConfirmButton.removeAttribute('disabled');
    codeModalCancelButton.removeAttribute('disabled');
    codeModalCloseButton.removeAttribute('disabled');
    codeModalSpinner.setAttribute("hidden", "");

    radioModalConfirmButton.removeAttribute('disabled');
    radioModalCancelButton.removeAttribute('disabled');
    radioModalCloseButton.removeAttribute('disabled');
    radioModalSpinner.setAttribute("hidden", "");

    selectModalConfirmButton.removeAttribute('disabled');
    selectModalCancelButton.removeAttribute('disabled');
    selectModalCloseButton.removeAttribute('disabled');
    selectModalSpinner.setAttribute("hidden", "");
}

