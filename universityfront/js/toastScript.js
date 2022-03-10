$('#toast-placeholder').load('toast.html', function () {
    $(this).children(':first').unwrap();

    // Once the DOM is properly set up
    $(document).ready(function () {

        var buttonClickedId;


        var toastElementsList = [].slice.call(document.querySelectorAll('.toast'))
        var toastList = toastElementsList.map(function (toastElement) {
            return new bootstrap.Toast(toastElement);
        });
        toast = toastList[0];


        codeModalConfirmButton.addEventListener("click", customizeToast)
        radioModalConfirmButton.addEventListener("click", customizeToast)
        selectModalConfirmButton.addEventListener("click", customizeToast)
        newOrEditPersonModalConfirmButton.addEventListener("click", customizeToast)
        newOrEditStudyModalConfirmButton.addEventListener("click", customizeToast)


        let buttonsArray = $('#buttons').find("button").not("[id*='Modal']");
        console.log(buttonsArray)
        for (let index = 0; index < buttonsArray.length; index++) {
            buttonsArray[index].addEventListener("click", lastActionButtonClicked)
        }
    });

});


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
    codeModalConfirmButton.removeEventListener('click', giveCreditsFunction, false)

    //codeModalConfirmButton.removeEventListener('click', testFunction, false)

    radioModalConfirmButton.removeEventListener('click', toggleIsExamMadeFunction, false)
    radioModalConfirmButton.removeEventListener('click', toggleIsExamTakenFunction, false)


    newOrEditPersonModalConfirmButton.removeEventListener('click', newEntryPersonFunction, false)
    newOrEditPersonModalConfirmButton.removeEventListener('click', editEntryPersonFunction, false)

    newOrEditStudyModalConfirmButton.removeEventListener('click', newEntryStudyFunction, false)
    newOrEditPersonModalConfirmButton.removeEventListener('click', editEntryPersonFunction, false)


    switch (buttonClickedId) {
        case "giveCreditsButton":
            codeModalInput.value = "5";
            codeModalInputLabel.textContent = "Credits to give to the student(s)"
            codeModalConfirmButton.addEventListener('click', giveCreditsFunction);
            break;
        case "giveDiplomaButton":
            selectModalInput.value = "5";
            selectModalInputLabel.textContent = "Diploma to give to the student(s)"
            selectModalConfirmButton.addEventListener("click", giveDiplomaFunction);
            break;
        case "promotePersonButton":
            /* codeModalInput.value = "P1"
            codeModalInputLabel.textContent = "Code of the new professor"
            codeModalConfirmButton.addEventListener('click', setNewProfessorFunction); */
            break;
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
        case "newEntryPersonButton":
            if (document.URL.endsWith("lookup_students.html")) {
                newOrEditPersonModalLastNameInputLabel.textContent = "Last name of the student to add"
                newOrEditPersonModalFirstNameInputLabel.textContent = "First name of the student to add"
                newOrEditPersonModalGenderSelectInputLabel.textContent = "Gender of the student to add"
                newOrEditPersonModalBirthdateInputLabel.textContent = "Birthdate of the student to add"
                newOrEditPersonModalEmailInputLabel.textContent = "Email of the student to add"
                newOrEditPersonModalPhoneInputLabel.textContent = "Phone number of the student to add"
            } else {
                newOrEditPersonModalLastNameInputLabel.textContent = "Last name of the professor to add"
                newOrEditPersonModalFirstNameInputLabel.textContent = "First name of the professor to add"
                newOrEditPersonModalGenderSelectInputLabel.textContent = "Gender of the professor to add"
                newOrEditPersonModalBirthdateInputLabel.textContent = "Birthdate of the professor to add"
                newOrEditPersonModalEmailInputLabel.textContent = "Email of the professor to add"
                newOrEditPersonModalPhoneInputLabel.textContent = "Phone number of the professor to add"
            }
            newOrEditPersonModalConfirmButton.addEventListener('click', newEntryPersonFunction);
            break;
        case "newEntryStudyButton":
            if (document.URL.endsWith("lookup_courses.html")) {
                newOrEditStudyModalHeadingInputLabel.textContent = "Heading of the course to add"
                newOrEditStudyModalDepartmentSelectInputLabel.textContent = "Department of the course to add"
            } else {
                newOrEditStudyModalHeadingInputLabel.textContent = "Heading of the degree to add"
                newOrEditStudyModalDepartmentSelectInputLabel.textContent = "Department of the degree to add"
            }
            newOrEditStudyModalConfirmButton.addEventListener('click', newEntryStudyFunction);
            break;
        case "editEntryPersonButton":
            if (document.URL.endsWith("detail_student.html")) {
                newOrEditPersonModalLastNameInputLabel.textContent = "Last name of the student to edit"
                newOrEditPersonModalFirstNameInputLabel.textContent = "First name of the student to edit"
                newOrEditPersonModalGenderSelectInputLabel.textContent = "Gender of the student to edit"
                newOrEditPersonModalBirthdateInputLabel.textContent = "Birthdate of the student to edit"
                newOrEditPersonModalEmailInputLabel.textContent = "Email of the student to edit"
                newOrEditPersonModalPhoneInputLabel.textContent = "Phone number of the student to edit"
            } else {
                newOrEditPersonModalLastNameInputLabel.textContent = "Last name of the professor to edit"
                newOrEditPersonModalFirstNameInputLabel.textContent = "First name of the professor to edit"
                newOrEditPersonModalGenderSelectInputLabel.textContent = "Gender of the professor to edit"
                newOrEditPersonModalBirthdateInputLabel.textContent = "Birthdate of the professor to edit"
                newOrEditPersonModalEmailInputLabel.textContent = "Email of the professor to edit"
                newOrEditPersonModalPhoneInputLabel.textContent = "Phone number of the professor to edit"
            }
            newOrEditPersonModalConfirmButton.addEventListener('click', editEntryPersonFunction);
            break;
        case "editEntryStudyButton":
            if (document.URL.endsWith("detail_course.html")) {
                newOrEditStudyModalHeadingInputLabel.textContent = "Heading of the course to edit"
                newOrEditStudyModalDepartmentSelectInputLabel.textContent = "Department of the course to edit"
            } else {
                newOrEditStudyModalHeadingInputLabel.textContent = "Heading of the degree to edit"
                newOrEditStudyModalDepartmentSelectInputLabel.textContent = "Department of the degree to edit"
            }
            newOrEditStudyModalConfirmButton.addEventListener('click', editEntryStudyFunction);
            break;
    }

}


function customizeToast() {

    let operationHeader = "";
    let operationBody = "";

    console.log(buttonClickedId)
    switch (buttonClickedId) {
        case "giveDiplomaButton":
            operationHeader = "Give diploma"
            operationBody = "diploma given";
            break;
        case "giveCreditsButton":
            operationHeader = "Give credits"
            operationBody = "credits given";
            break;
        case "giveWarningPersonButton":
            operationHeader = "Give warning"
            operationBody = "warning given";
            break;
        case "kickOutPersonButton":
            operationHeader = "Kick out"
            operationBody = "person kicked out";
            break;
        case "demotePersonButton":
            if (document.URL.endsWith("lookup_students.html")) {
                operationHeader = "Demote student(s)"
                operationBody = "student(s) demoted";
            } else {
                operationHeader = "Demote professor(s)"
                operationBody = "professor(s) demoted";
            }
            break;
        case "promotePersonButton":
            if (document.URL.endsWith("lookup_students.html")) {
                operationHeader = "Promote student(s)"
                operationBody = "student(s) promoted";
            } else {
                operationHeader = "Promote professor(s)"
                operationBody = "professor(s) promoted";
            }
            break;
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
        case "newEntryPersonButton":
            if (document.URL.endsWith("lookup_students.html")) {
                operationHeader = "Add new student"
                operationBody = "new student added";
            } else {
                operationHeader = "Add new professor"
                operationBody = "new professor added";
            }
            break;
        case "newEntryStudyButton":
            if (document.URL.endsWith("lookup_courses.html")) {
                operationHeader = "Add new course"
                operationBody = "new course added";
            } else {
                operationHeader = "Add new degree"
                operationBody = "new degree added";
            }
            break;
        case "editEntryPersonButton":
            if (document.URL.endsWith("detail_student.html")) {
                operationHeader = "Edit student"
                operationBody = "student edited";
            } else {
                operationHeader = "Edit professor"
                operationBody = "professor edited";
            }
            break;
        case "editEntryStudyButton":
            if (document.URL.endsWith("detail_course.html")) {
                operationHeader = "Edit course"
                operationBody = "course edited";
            } else {
                operationHeader = "Edit degree"
                operationBody = "degree edited";
            }
            break;
    }

    toastHeaderText.textContent = operationHeader + " notification";
    toastBodyText.textContent = "Operation successful: " + operationBody + ".";
    var date = new Date();
    toastHeaderTime.textContent = date.getHours() + ":" + date.getMinutes();
    toastHeaderIcon.classList.remove("text-danger");
    toastHeaderIcon.classList.add("text-success");
}


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
        $('#newOrEditPersonModal').modal('hide');
        $('#newOrEditStudyModal').modal('hide');
        toast.show();
        return true;
    } else if (data.status == 400) {
        newOrEditStudyModalDepartmentSelectInputOption.textContent = "> " + newOrEditStudyModalDepartmentSelectInputOption.textContent.toUpperCase();
        newOrEditPersonModalGenderSelectInputOption.textContent = "> " + newOrEditPersonModalGenderSelectInputOption.textContent.toUpperCase();
        selectModalInputOption.textContent = "> " + selectModalInputOption.textContent.toUpperCase();
        return true;
    } else {
        $('#codeModal').modal('hide');
        $('#radioModal').modal('hide');
        $('#selectModal').modal('hide');
        $('#newOrEditPersonModal').modal('hide');
        $('#newOrEditStudyModal').modal('hide');
        toast.show();
        return false;
    }
}
