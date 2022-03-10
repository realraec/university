$('#modals-placeholder').load('modals.html', function () {
    $(this).children(':first').unwrap();
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

    newOrEditPersonModalConfirmButton.setAttribute("disabled", "true");
    newOrEditPersonModalCancelButton.setAttribute("disabled", "true");
    newOrEditPersonModalCloseButton.setAttribute("disabled", "true");
    newOrEditPersonModalSpinner.removeAttribute("hidden");

    newOrEditStudyModalConfirmButton.setAttribute("disabled", "true");
    newOrEditStudyModalCancelButton.setAttribute("disabled", "true");
    newOrEditStudyModalCloseButton.setAttribute("disabled", "true");
    newOrEditStudyModalSpinner.removeAttribute("hidden");
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

    newOrEditPersonModalConfirmButton.removeAttribute('disabled');
    newOrEditPersonModalCancelButton.removeAttribute('disabled');
    newOrEditPersonModalCloseButton.removeAttribute('disabled');
    newOrEditPersonModalSpinner.setAttribute("hidden", "");

    newOrEditStudyModalConfirmButton.removeAttribute('disabled');
    newOrEditStudyModalCancelButton.removeAttribute('disabled');
    newOrEditStudyModalCloseButton.removeAttribute('disabled');
    newOrEditStudyModalSpinner.setAttribute("hidden", "");
}
