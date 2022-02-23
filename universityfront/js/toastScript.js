$('#toast-placeholder').load('toast.html', function () {
    $(this).children(':first').unwrap();


    // Global constants
    const toastBodyText = document.getElementById('toastBodyText');
    const toastHeaderIcon = document.getElementById('toastHeaderIcon');
    const toastHeaderTime = document.getElementById('toastHeaderTime');
    const toastHeaderText = document.getElementById('toastHeaderText');



    var toastElementsList = [].slice.call(document.querySelectorAll('.toast'))
    var toastList = toastElementsList.map(function (toastElement) {
        return new bootstrap.Toast(toastElement);
    });
    toast = toastList[0];






    //const modalConfirmButton = document.getElementById('modalConfirmButton');
    codeModalConfirmButton.addEventListener("click", customizeToast)
    radioModalConfirmButton.addEventListener("click", customizeToast)
    selectModalConfirmButton.addEventListener("click", customizeToast)


    let buttonsArray = $('#buttons').find("button").not("[id*='Modal']");
    console.log(buttonsArray)
    for (let index = 0; index < buttonsArray.length; index++) {
        buttonsArray[index].addEventListener("click", lastActionButtonClicked)
    }


    var buttonClickedId;

    function lastActionButtonClicked(e) {
        buttonClickedId = e.currentTarget.id;
        console.log(buttonClickedId)

        codeModalConfirmButton.removeEventListener('click', addStudentFunction, false)
        codeModalConfirmButton.removeEventListener('click', removeStudentFunction, false)
        codeModalConfirmButton.removeEventListener('click', addCourseFunction, false)
        codeModalConfirmButton.removeEventListener('click', removeCourseFunction, false)
        codeModalConfirmButton.removeEventListener('click', setNewProfessorFunction, false)
        codeModalConfirmButton.removeEventListener('click', setNewMajorDegreeFunction, false)
        codeModalConfirmButton.removeEventListener('click', setNewMinorDegreeFunction, false)

        //codeModalConfirmButton.removeEventListener('click', testFunction, false)

        radioModalConfirmButton.removeEventListener('click', toggleIsExamMadeFunction, false)
        radioModalConfirmButton.removeEventListener('click', toggleIsExamTakenFunction, false)



        switch (buttonClickedId) {
            case "setNewProfessorButton":
                codeModalInput.value = "P1"
                codeModalInputLabel.textContent = "Code of the new professor"
                codeModalConfirmButton.addEventListener('click', setNewProfessorFunction);
                break;
            case "setNewMajorDegreeButton":
                codeModalInput.value = "D1"
                codeModalInputLabel.textContent = "Code of the new major degree"
                codeModalConfirmButton.addEventListener('click', setNewMajorDegreeFunction);
                break;
            case "setNewMinorDegreeButton":
                codeModalInput.value = "D2"
                codeModalInputLabel.textContent = "Code of the new minor degree"
                codeModalConfirmButton.addEventListener('click', setNewMinorDegreeFunction);
                break;
            case "testButtonABC":
                selectModalInputOption.textContent = "testtt"
                selectModalInputLabel.textContent = "test"
                selectModalConfirmButton.addEventListener('click', getDepartments);
                break;
            case "toggleIsExamMadeButton":
                radioModalInputLabel.textContent = "State of the exam (made)"
                radioModalInputRadio1Label.innerHTML = "<i class='bi bi-check-circle ms-1 me-2'></i>Made"
                radioModalInputRadio2Label.innerHTML = "<i class='bi bi-x-circle-fill ms-1 me-2'></i>Not made"
                radioModalConfirmButton.addEventListener('click', toggleIsExamMadeFunction);
                break;
            case "toggleIsExamTakenButton":
                radioModalInputLabel.textContent = "State of the exam (taken)"
                radioModalInputRadio1Label.innerHTML = "<i class='bi bi-check-circle ms-1 me-2'></i>Taken"
                radioModalInputRadio2Label.innerHTML = "<i class='bi bi-x-circle-fill ms-1 me-2'></i>Not taken"
                radioModalConfirmButton.addEventListener('click', toggleIsExamTakenFunction);
                break;
            case "addStudentButton":
                codeModalInput.value = "S1"
                codeModalInputLabel.textContent = "Code of the student to add"
                codeModalConfirmButton.addEventListener('click', addStudentFunction);
                break;
            case "removeStudentButton":
                codeModalInput.value = "S1"
                codeModalInputLabel.textContent = "Code of the student to remove"
                codeModalConfirmButton.addEventListener('click', removeStudentFunction);
                break;
            case "addCourseButton":
                codeModalInput.value = "C1"
                codeModalInputLabel.textContent = "Code of the course to add"
                codeModalConfirmButton.addEventListener('click', addCourseFunction);
                break;
            case "removeCourseButton":
                codeModalInput.value = "C1"
                codeModalInputLabel.textContent = "Code of the course to remove"
                codeModalConfirmButton.addEventListener('click', removeCourseFunction);
                break;

        }


    }

    function customizeToast() {

        let operationHeader = "";
        let operationBody = "";

        console.log(buttonClickedId)
        switch (buttonClickedId) {
            case "setNewProfessorButton":
                operationHeader = "New professor"
                operationBody = "professor changed";
                break;
            case "setNewMajorDegreeButton":
                operationHeader = "New major degree"
                operationBody = "majors changed";
                break;
            case "setNewMinorDegreeButton":
                operationHeader = "New minor degree"
                operationBody = "minors changed";
                break;
            case "testButtonABC":
                operationHeader = "Button test"
                operationBody = "button tested";
                break;
            case "toggleIsExamMadeButton":
                operationHeader = "Toggle exam made"
                operationBody = "exam made toggled";
                break;
            case "toggleIsExamTakenButton":
                operationHeader = "Toggle exam taken"
                operationBody = "exam taken toggled";
                break;
            case "addStudentButton":
                operationHeader = "Add student"
                operationBody = "student added";
                break;
            case "removeStudentButton":
                operationHeader = "Remove student"
                operationBody = "student removed";
                break;
            case "addCourseButton":
                operationHeader = "Add course"
                operationBody = "course added";
                break;
            case "removeCourseButton":
                operationHeader = "Remove course"
                operationBody = "course removed";
                break;
        }

        toastHeaderText.textContent = operationHeader + " notification";
        toastBodyText.textContent = "Operation successful: " + operationBody + ".";
        var date = new Date();
        toastHeaderTime.textContent = date.getHours() + ":" + date.getMinutes();
        toastHeaderIcon.classList.remove("text-danger");
        toastHeaderIcon.classList.add("text-success");
    }


});


function checkIfErrorOccurred(data) {
    // If the Java method throws an exception
    if (data.status == 500) {
        toastBodyText.textContent = "Operation failed: " + data.message;
        toastHeaderIcon.classList.remove("text-success");
        toastHeaderIcon.classList.add("text-danger");
        //console.log(data)
        $('#codeModal').modal('hide');
        $('#radioModal').modal('hide');
        $('#selectModal').modal('hide');
        toast.show();
        return true;
    } else {
        $('#codeModal').modal('hide');
        $('#radioModal').modal('hide');
        $('#selectModal').modal('hide');
        toast.show();
        return false;
    }
}
