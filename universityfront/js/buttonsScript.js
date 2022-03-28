$('#buttons-placeholder').load('buttons.html', function () {
  $(this).children(':first').unwrap();


  $(document).ready();

});


function initButtonsAndMisc() {

  let documentURL = document.URL;
  if (documentURL.includes("student")) {

    extraButtons.remove();
    examsButtons.remove();
    studentsButtons.remove();
    coursesButtons.remove();
    TBDButtons.remove();
    newEntryStudyButton.remove();
    editEntryStudyButton.remove();

    if (documentURL.includes("detail_")) {
      // Detail-specific
      newEntryPersonButton.disabled = true;
      editEntryPersonButton.addEventListener('click', getGenders);
      editEntryPersonButton.addEventListener('click', editEntryPersonPreparation);

      detailPagesVisibilityFunction();

      checkoutEntitySelectQuery = "#minorDegreeInput, #majorDegreeInput, #coursesInput, td:nth-of-type(6), td:nth-of-type(7)";

    } else {
      // Lookup-specfic
      editEntryPersonButton.disabled = true;
      newEntryPersonButton.addEventListener('click', getGenders);
    }

    buttons.classList.remove("invisible");

    promotePersonButton.addEventListener('click', promoteOrDemotePersonFunction);
    demotePersonButton.addEventListener('click', promoteOrDemotePersonFunction);
    giveWarningPersonButton.addEventListener('click', giveWarningOrKickOutPersonFunction);
    kickOutPersonButton.addEventListener('click', giveWarningOrKickOutPersonFunction);
    giveDiplomaButton.addEventListener("click", getDiplomas);

    deleteButton.addEventListener('click', deleteEntryFunction);
    refreshEntryButton.addEventListener('click', refreshEntryFunction);
    loadExtraInfoButton.addEventListener('click', loadCoursesForPersonsFunction);
    sendEmailButton.addEventListener('click', emailPersons);

  } else if (documentURL.includes("professor")) {

    rewardButtons.remove();
    extraButtons.remove();
    examsButtons.remove();
    studentsButtons.remove();
    coursesButtons.remove();
    degreesButtons.remove();
    TBDButtons.remove();
    newEntryStudyButton.remove();
    editEntryStudyButton.remove();

    if (documentURL.includes("detail_")) {
      // Detail-specific
      newEntryPersonButton.disabled = true;
      editEntryPersonButton.addEventListener('click', getGenders)
      editEntryPersonButton.addEventListener('click', editEntryPersonPreparation);

      detailPagesVisibilityFunction();

      checkoutEntitySelectQuery = "#coursesInput";
    } else {
      // Lookup-specfic
      editEntryPersonButton.disabled = true;
      newEntryPersonButton.addEventListener('click', getGenders)
    }

    buttons.classList.remove("invisible");

    promotePersonButton.addEventListener('click', promoteOrDemotePersonFunction);
    demotePersonButton.addEventListener('click', promoteOrDemotePersonFunction);
    giveWarningPersonButton.addEventListener('click', giveWarningOrKickOutPersonFunction);
    kickOutPersonButton.addEventListener('click', giveWarningOrKickOutPersonFunction);
    deleteButton.addEventListener('click', deleteEntryFunction);
    refreshEntryButton.addEventListener('click', refreshEntryFunction);
    loadExtraInfoButton.addEventListener('click', loadCoursesForPersonsFunction);
    sendEmailButton.addEventListener('click', emailPersons);

  } else if (documentURL.includes("course")) {

    degreesButtons.remove();
    levelButtons.remove();
    sanctionButtons.remove();
    rewardButtons.remove();
    TBDButtons.remove();
    coursesButtons.remove();
    newEntryPersonButton.remove();
    editEntryPersonButton.remove();

    if (documentURL.includes("detail_")) {
      // Detail-specific
      newEntryStudyButton.disabled = true;
      editEntryStudyButton.addEventListener('click', getDepartments)
      editEntryStudyButton.addEventListener('click', editEntryStudyPreparation)

      detailPagesVisibilityFunction();

      checkoutEntitySelectQuery = "#professorInput, #studentsInput, #degreeInput, td:nth-of-type(6), td:nth-of-type(7)";

    } else {
      // Lookup-specfic
      editEntryStudyButton.disabled = true;
      newEntryStudyButton.addEventListener('click', getDepartments)
    }

    buttons.classList.remove("invisible");

    deleteButton.addEventListener('click', deleteEntryFunction);
    refreshEntryButton.addEventListener('click', refreshEntryFunction);
    loadExtraInfoButton.addEventListener('click', loadDegreeForCoursesFunction);
    sendEmailButton.addEventListener('click', emailPersons);

  } else //if (documentURL.includes("degree"))
  {

    degreesButtons.remove();
    levelButtons.remove();
    sanctionButtons.remove();
    rewardButtons.remove();
    TBDButtons.remove();
    extraButtons.remove();
    examsButtons.remove();
    studentsButtons.remove();
    newEntryPersonButton.remove();
    editEntryPersonButton.remove();

    if (documentURL.includes("detail_")) {
      // Detail-specific
      newEntryStudyButton.disabled = true;
      editEntryStudyButton.addEventListener('click', getDepartments)
      editEntryStudyButton.addEventListener('click', editEntryStudyPreparation)

      detailPagesVisibilityFunction();

      checkoutEntitySelectQuery = "#coursesInput, #professorsInput, #studentsInput, td:nth-of-type(4)";

    } else {
      // Lookup-specfic
      editEntryStudyButton.disabled = true;
      addCourseButton.disabled = true;
      removeCourseButton.disabled = true;
      newEntryStudyButton.addEventListener('click', getDepartments)
    }

    buttons.classList.remove("invisible");

    deleteButton.addEventListener('click', deleteEntryFunction);
    refreshEntryButton.addEventListener('click', refreshEntryFunction);
    loadExtraInfoButton.addEventListener('click', loadStudentsAndProfessorsForDegreesFunction);
    loadExtraInfoButton.addEventListener('click', changeFormatterCoursesForDegree);
    sendEmailButton.addEventListener('click', emailPersons);

  }
}


function updateHistoryWithChanges(table) {
  // Fetching changed rows
  let newCurrentRows = table.bootstrapTable('getData');

  // Fetching saved history
  var history = JSON.parse(sessionStorage.getItem('history'))

  // Modified rows
  for (let i = 0; i < newCurrentRows.length; i++) {

    // History tables
    for (let j = 0; j < history.length; j++) {

      // History rows
      for (let k = 0; k < history[j].length; k++) {

        if (newCurrentRows[i].id == history[j][k].id) {
          // Updating the item of the history
          history[j].splice(k, 1, newCurrentRows[i]);
        }
      }
    }
  }

  // Saving the changes
  sessionStorage.setItem('history', JSON.stringify(history));
}


async function addOrRemoveStudentFunction() {

  let addOrRemove = "";
  if (buttonClickedId == 'addStudentButton') {
    addOrRemove = "add";
  } else {
    addOrRemove = "remove";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/' + addOrRemove + 'Student', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "studentCode": codeModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.student;

      // Update the current table adding the student to the array
      for (let index = 0; index < currentRows.length; index++) {

        let studentsSet = [...currentRows[index].students];
        if (addOrRemove == "add") {
          studentsSet.push(temp);
        } else {
          studentsSet = studentsSet.filter(function (object) {
            return object.id !== temp.id;
          });
        }

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'students',
          value: studentsSet
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        let data = $table.bootstrapTable('getData')[0].students;
        document.getElementById("studentsInput").value = (data[0] == null ? null : twoPersonsPerLineFormatter(data).replaceAll("<br/>", " — "));
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    })
};


async function addOrRemoveCourseFunction() {

  let addOrRemove = "";
  if (buttonClickedId == 'addCourseButton') {
    addOrRemove = "add";
  } else {
    addOrRemove = "remove";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/degrees/' + addOrRemove + 'Course', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "degreesIdList": currentRowsId,
      "courseCode": codeModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.course;

      // Update the current table adding the student to the array
      for (let index = 0; index < currentRows.length; index++) {
        let coursesSet = [...currentRows[index].courses];

        if (addOrRemove == "add") {
          coursesSet.push(temp);
        } else {
          coursesSet = coursesSet.filter(function (object) {
            return object.id !== temp.id;
          });
        }

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'courses',
          value: coursesSet
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        let data = $table.bootstrapTable('getData')[0].courses;
        document.getElementById("coursesInput").value = (data[0] == null ? null : threeStudiesPerLineFormatter(data).replaceAll("<br/>", " — "));
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    })
};


async function setNewProfessorOrDegreeFunction() {

  let professorOrDegree = "";
  let field = "";
  if (buttonClickedId == 'setNewProfessorButton') {
    professorOrDegree = "Professor";
    field = "professor";
  } else {
    professorOrDegree = "Degree";
    field = "degree";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/setNew' + professorOrDegree, {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "entityCode": codeModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp;

      if (professorOrDegree == "Professor") {
        temp = data.data.professor;
      } else {
        temp = data.data.degree;
      }

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: field,
          value: temp
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        if (professorOrDegree == "Professor") {
          document.getElementById(field + "Input").value = onePersonPerColumnFormatter(temp).replaceAll("<br/>", " ");
        } else {
          $table.bootstrapTable('showColumn', 'degree');
          document.getElementById(field + "Input").value = oneStudyPerColumnFormatter(temp).replaceAll("<br/>", " ");
          if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(8)")) {
            checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(8)";
          }

        }
        customContextMenu(checkoutEntitySelectQuery);

      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }
    })
};


async function setNewMajorOrMinorDegreeFunction() {

  let majorOrMinor = "";
  let field = "";
  if (buttonClickedId == 'setNewMajorDegreeButton') {
    majorOrMinor = "Major";
    field = "majorDegree";
  } else {
    majorOrMinor = "Minor";
    field = "minorDegree";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/students/setNew' + majorOrMinor + 'Degree', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "studentsIdList": currentRowsId,
      "degreeCode": codeModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.degree;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: field,
          value: temp
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById(field + "Input").value = oneStudyPerColumnFormatter(temp);
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }
    })
};



async function getGenders() {

  newOrEditPersonModalGenderSelectInput.setAttribute("disabled", "true");

  // Performing the operation
  const response = await fetch('http://localhost:8080/misc/getGenders', {
    method: 'get',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    }
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // If the Java method throws an exception
      if (data.status == 500) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.genders;
      console.log(temp)

      // Removing the possibly previously-fetched values
      //$('#selectModalInput').find('option').not(':first').remove();
      $('#newOrEditPersonModalGenderSelectInput').find('option').not(':first').remove();
      let optionIndexToSelect = 0;
      let documentURL = document.URL;

      // Adding the newly-fetched values
      for (let i = 0; i < temp.length; i++) {
        var option = temp[i];
        var element = document.createElement("option");
        element.textContent = option;
        element.value = option;
        selectModalInput.appendChild(element);
        newOrEditPersonModalGenderSelectInput.appendChild(element);

        if (documentURL.endsWith("detail_student.html") || documentURL.endsWith("detail_professor.html")) {
          if (option.endsWith("_" + genderInput.value.toUpperCase())) {
            optionIndexToSelect = i + 1;
          }
        }
      }

      newOrEditPersonModalGenderSelectInput.options[optionIndexToSelect].selected = "true"
      newOrEditPersonModalGenderSelectInput.removeAttribute('disabled');

    });
};


async function getDepartments() {

  newOrEditStudyModalDepartmentSelectInput.setAttribute("disabled", "true");

  // Performing the operation
  const response = await fetch('http://localhost:8080/misc/getDepartments', {
    method: 'get',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    }
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // If the Java method throws an exception
      if (data.status == 500) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.departments;
      console.log(temp)

      // Removing the possibly previously-fetched values
      //$('#selectModalInput').find('option').not(':first').remove();
      $('#newOrEditStudyModalDepartmentSelectInput').find('option').not(':first').remove();
      let optionIndexToSelect = 0;
      let documentURL = document.URL;

      // Adding the newly-fetched values
      for (let i = 0; i < temp.length; i++) {
        var option = temp[i];
        var element = document.createElement("option");
        element.textContent = option;
        element.value = option;
        selectModalInput.appendChild(element);
        newOrEditStudyModalDepartmentSelectInput.appendChild(element);


        if (documentURL.endsWith("detail_course.html") || documentURL.endsWith("detail_degree.html")) {
          if (option.endsWith("_" + departmentInput.value.toUpperCase().replaceAll(" ", "_"))) {
            optionIndexToSelect = i + 1;
          }
        }
      }

      newOrEditStudyModalDepartmentSelectInput.options[optionIndexToSelect].selected = "true"
      newOrEditStudyModalDepartmentSelectInput.removeAttribute('disabled');

    });
};


async function getDiplomas() {

  selectModalInput.setAttribute("disabled", "true");

  // Performing the operation
  const response = await fetch('http://localhost:8080/misc/getDiplomas', {
    method: 'get',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    }
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // If the Java method throws an exception
      if (data.status == 500) {
        return;
      }

      // Removing the possibly previously-fetched values
      $('#selectModalInput').find('option').not(':first').remove();
      let optionIndexToSelect = 0;
      let documentURL = document.URL;

      // Extracting the new value from the response
      let temp = data.data.diplomas;
      console.log(temp)

      for (let i = 0; i < temp.length; i++) {
        var option = temp[i];
        var element = document.createElement("option");
        element.textContent = option;
        element.value = option;
        selectModalInput.appendChild(element)
      }

      selectModalInput.options[optionIndexToSelect].selected = "true"
      selectModalInput.removeAttribute('disabled');

    });
};



async function newEntryPersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
    personType = "student";
  } else {
    personType = "professor";
  }

  var history = JSON.parse(sessionStorage.getItem('history'))

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/' + personType + 's/create', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "lastName": newOrEditPersonModalLastNameInput.value,
      "firstName": newOrEditPersonModalFirstNameInput.value,
      "gender": newOrEditPersonModalGenderSelectInput.value,
      "birthdate": newOrEditPersonModalBirthdateInput.value,
      "email": newOrEditPersonModalEmailInput.value,
      "phone": newOrEditPersonModalPhoneInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data)

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.person;
      for (let i = 0; i < history.length; i++) {
        history[i].push(temp);
      }

      sessionStorage.setItem('history', JSON.stringify(history));
      console.log(history)
      $table.bootstrapTable('append', temp);
    });
};


async function newEntryStudyFunction() {

  let studyType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_courses.html")) {
    studyType = "course";
  } else {
    studyType = "degree";
  }

  var history = JSON.parse(sessionStorage.getItem('history'))

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/' + studyType + 's/create', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "heading": newOrEditStudyModalHeadingInput.value,
      "department": newOrEditStudyModalDepartmentSelectInput.value,
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data)

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.study;
      for (let i = 0; i < history.length; i++) {
        history[i].push(temp);
      }

      sessionStorage.setItem('history', JSON.stringify(history));
      $table.bootstrapTable('append', temp);
    });
};


function editEntryPersonPreparation() {
  newOrEditPersonModalLastNameInput.value = lastNameInput.value
  newOrEditPersonModalFirstNameInput.value = firstNameInput.value
  newOrEditPersonModalBirthdateInput.value = birthdateInput.value
  newOrEditPersonModalEmailInput.value = emailInput.value
  newOrEditPersonModalPhoneInput.value = phoneInput.value
};


async function editEntryPersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("detail_student.html")) {
    personType = "S";
  } else {
    personType = "P";
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/persons/edit', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "personId": idInput.value,
      "personType": personType,
      "personLastName": newOrEditPersonModalLastNameInput.value,
      "personFirstName": newOrEditPersonModalFirstNameInput.value,
      "personGender": newOrEditPersonModalGenderSelectInput.value,
      "personBirthdate": newOrEditPersonModalBirthdateInput.value,
      "personEmail": newOrEditPersonModalEmailInput.value,
      "personPhone": newOrEditPersonModalPhoneInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data)

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.person;
      console.log(temp)
      $table.bootstrapTable('load', [temp]);

      if (personType == "S") {
        updateDetailStudent([temp]);
      } else {
        updateDetailProfessor([temp]);
      }
    });
};


function editEntryStudyPreparation() {
  newOrEditStudyModalHeadingInput.value = headingInput.value
};


async function editEntryStudyFunction() {

  let studyType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("detail_course.html")) {
    studyType = "C";
  } else {
    studyType = "D";
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/studies/edit', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "studyId": idInput.value,
      "studyType": studyType,
      "studyHeading": newOrEditStudyModalHeadingInput.value,
      "studyDepartment": newOrEditStudyModalDepartmentSelectInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data)

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.study;
      console.log(temp)
      $table.bootstrapTable('load', [temp]);

      if (studyType == "C") {
        updateDetailCourse([temp]);
      } else {
        updateDetailDegree([temp]);
      }
    });
};


async function refreshEntryFunction() {

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    entityType = "S";
  } else if (documentURL.includes("professor")) {
    entityType = "P";
  } else if (documentURL.includes("course")) {
    entityType = "C";
  } else {
    entityType = "D";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/entities/refresh', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "entitiesIdList": currentRowsId,
      "entityType": entityType
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new values from the response
      let temp = data.data.entities;

      // Update the current table with the changed value returned
      $table.bootstrapTable('load', temp)

      // Update history with the changes made
      updateHistoryWithChanges($table)

      // Updating detail part
      if (documentURL.includes('detail_')) {
        let data = $table.bootstrapTable('getData');
        if (entityType == "S") {
          updateDetailStudent(data);
        } else if (entityType == "P") {
          updateDetailProfessor(data);
        } else if (entityType == "C") {
          updateDetailCourse(data);
        } else {
          updateDetailDegree(data);
        }
        customContextMenu(checkoutEntitySelectQuery);
      }

    });
}


async function deleteEntryFunction() {

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    entityType = "student";
  } else if (documentURL.includes("professor")) {
    entityType = "professor";
  } else if (documentURL.includes("course")) {
    entityType = "course";
  } else {
    entityType = "degree";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/' + entityType + 's/delete', {
    method: 'delete',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      entitiesIdList: currentRowsId,
      //"entityType": entityType
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }


      // Update history with the changes made
      var history = JSON.parse(sessionStorage.getItem('history'))

      // Rows edited
      for (let i = 0; i < currentRows.length; i++) {

        // History tables
        for (let j = 0; j < history.length; j++) {

          // History rows
          for (let k = 0; k < history[j].length; k++) {

            if (currentRows[i].id == history[j][k].id) {
              // Updating the item of the history
              history[j].splice(k, 1);
              break;
            }

          }
        }
      }

      sessionStorage.setItem('history', JSON.stringify(history));

      $table.bootstrapTable('load', [])

      if (documentURL.includes('detail_')) {
        if (entityType == "student") {
          wipeOutDetailStudent($table);
        } else if (entityType == "professor") {
          wipeOutDetailProfessor($table);
        } else if (entityType == "course") {
          wipeOutDetailCourse($table);
        } else {
          wipeOutDetailDegree($table);
        }
      }

    });
}


async function promoteOrDemotePersonFunction(e) {

  let promoteOrDemote = "";
  if (e.currentTarget.id == 'promotePersonButton') {
    promoteOrDemote = "promote";
  } else {
    promoteOrDemote = "demote";
  }

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    personType = "S";
  } else {
    personType = "P";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/persons/' + promoteOrDemote, {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "personsIdList": currentRowsId,
      "personType": personType
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new values from the response
      let temp = data.data.levels;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'level',
          value: temp[index]
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("levelInput").value = temp[0];
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    });
};


async function giveWarningOrKickOutPersonFunction(e) {

  let giveWarningOrKickOut = "";
  if (e.currentTarget.id == 'giveWarningPersonButton') {
    giveWarningOrKickOut = "giveWarning";
  } else {
    giveWarningOrKickOut = "kickOut";
  }

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    personType = "S";
  } else {
    personType = "P";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/persons/' + giveWarningOrKickOut, {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "personsIdList": currentRowsId,
      "personType": personType
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new values from the response
      let temp = data.data.warnings;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'warnings',
          value: (temp.length > 1) ? temp[index] : temp
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("warningsInput").value = temp[0];
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    });
};


async function giveCreditsFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/students/giveCredits', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "studentsIdList": currentRowsId,
      "credits": codeModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new values from the response
      let temp = data.data.credits;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'credits',
          value: temp[index]
        })
      }


      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("creditsInput").value = temp[0];
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    });
};


async function giveDiplomaFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/students/giveDiploma', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "studentsIdList": currentRowsId,
      "diploma": selectModalInput.value
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new values from the response
      let temp = data.data.diploma;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'diploma',
          value: temp
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("diplomaInput").value = diplomaFormatter(temp);
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    });

};


async function toggleIsExamMadeOrTakenFunction() {

  let attributeToSet = "";
  if (buttonClickedId == 'toggleIsExamMadeButton') {
    attributeToSet = "MadeByProfessor";
  } else {
    attributeToSet = "TakenByStudents";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/setIsExam' + attributeToSet, {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "isExamMadeOrTaken": radioModalInputRadio1.checked
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.boolean;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'isExam' + attributeToSet,
          value: temp
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        if (attributeToSet == "MadeByProfessor") {
          document.getElementById("examMadeInput").value = booleanFormatter(temp).replace(/<\/?[^>]+(>|$)/g, "");;
        } else {
          document.getElementById("examTakenInput").value = booleanFormatter(temp).replace(/<\/?[^>]+(>|$)/g, "");;
        }
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    });
};


async function loadCoursesForPersonsFunction() {

  $table.bootstrapTable('showColumn', 'courses');

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    entityType = "student";
  } else {
    entityType = "professor";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/' + entityType + 's/getCourses', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "personsIdList": currentRowsId
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.courses;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'courses',
          value: temp[index]
        })
      }

      // Updating detail part
      if (documentURL.includes('detail_')) {
        document.getElementById("coursesInput").value = (temp[0] == null ? null : threeStudiesPerLineFormatter(temp[0]).replaceAll("<br/>", " — "));
        if (entityType == "student") {
          if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(8)")) {
            checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(8)";
          }
        } else {
          if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(6)")) {
            checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(6)";
          }
        }
        customContextMenu(checkoutEntitySelectQuery)

      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

    })

}


async function loadDegreeForCoursesFunction() {

  $table.bootstrapTable('showColumn', 'degree');

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/getDegree', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.degree;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'degree',
          value: temp[index]
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("degreeInput").value = (temp[0] == null ? null : oneStudyPerColumnFormatter(temp[0]));
        if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(8)")) {
          checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(8)";
        }
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }

      // Scroll to the rightmost to show the newly-shown column
      var left = $('.fixed-table-body').width();
      $('.fixed-table-body').scrollLeft(left);
    })

}


function loadStudentsAndProfessorsForDegreesFunction() {
  loadStudentsForDegreesFunction();
  loadProfessorsForDegreesFunction();
  if (document.URL.includes("detail_")) {
    if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(5), td:nth-of-type(6)")) {
      checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(5), td:nth-of-type(6)";
    }
    customContextMenu(checkoutEntitySelectQuery);
  }
}


async function loadStudentsForDegreesFunction() {

  $table.bootstrapTable('showColumn', 'students');

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/degrees/getStudents', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "degreesIdList": currentRowsId
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.students;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'students',
          value: temp[index]
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("studentsInput").value = (temp[0] == null ? null : twoPersonsPerLineFormatter(temp[0]).replaceAll("<br/>", " — "));
        if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(5), td:nth-of-type(6)")) {
          checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(5), td:nth-of-type(6)";
        }
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }
    })

}


async function loadProfessorsForDegreesFunction() {

  $table.bootstrapTable('showColumn', 'professors');

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/degrees/getProfessors', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "degreesIdList": currentRowsId
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.professors;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'professors',
          value: temp[index]
        })
      }

      // Updating detail part
      if (document.URL.includes('detail_')) {
        document.getElementById("professorsInput").value = (temp[0] == null ? null : twoPersonsPerLineFormatter(temp[0]).replaceAll("<br/>", " — "));
        if (!checkoutEntitySelectQuery.endsWith(", td:nth-of-type(5), td:nth-of-type(6)")) {
          checkoutEntitySelectQuery = checkoutEntitySelectQuery + ", td:nth-of-type(5), td:nth-of-type(6)";
        }
        customContextMenu(checkoutEntitySelectQuery);
      } else {
        // Update history with the changes made
        updateHistoryWithChanges($table)
      }
    })

}


async function emailPersons() {

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.includes("student")) {
    entityType = "S";
  } else if (documentURL.includes("professor")) {
    entityType = "P";
  } else if (documentURL.includes("course")) {
    entityType = "C";
  } else {
    entityType = "D";
  }

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/entities/contact', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "entitiesIdList": currentRowsId,
      "entityType": entityType
    })
  }).then(response => response.json())
    .then(data => {
      console.log(data);

      // To let the user perform other actions afterward
      enableButtonsInModal();

      // If the Java method throws an exception
      if (checkIfErrorOccurred(data)) {
        return;
      }

      // Extracting the new value from the response
      let temp = data.data.emails;
      let stringToDisplayInAlert = "";

      for (let index = 0; index < temp.length; index++) {
        stringToDisplayInAlert += temp[index];
        if (index != temp.length - 1)
          stringToDisplayInAlert += "\n"
      }

      alert(stringToDisplayInAlert)
    })

}


function changeFormatterCoursesForDegree() {
  let temp = { ...$table.bootstrapTable('getOptions').columns };
  console.log(temp)
  if (document.URL.includes("detail_")) {
    temp[0][3].formatter = oneStudyPerLineFormatter;
  } else {
    temp[0][5].formatter = oneStudyPerLineFormatter;
  }
  $table.bootstrapTable('refreshOptions', { columns: temp });
}


function detailPagesVisibilityFunction() {
  document.addEventListener("visibilitychange", function () {
    if (!document.hidden) {
      console.log("Browser tab is visible")
      localStorage["detailId"] = JSON.stringify($table.bootstrapTable('getData')[0].id);
    }
  });
}


function checkoutDegreeFunction(e) {
  if (document.URL.includes("course")) {
    localStorage["detailId"] = $table.bootstrapTable('getData')[0].degree.id;
  } else {
    if (e.target.cellIndex == 6 || e.currentTarget.id == "minorDegreeInput") {
      localStorage["detailId"] = $table.bootstrapTable('getData')[0].minorDegree.id;
    } else {
      localStorage["detailId"] = $table.bootstrapTable('getData')[0].majorDegree.id;
    }
  }
  checkoutEntityButton.setAttribute('href', "detail_degree.html");
}

function checkoutProfessorFunction() {
  if (document.URL.includes("course")) {
    localStorage["detailId"] = $table.bootstrapTable('getData')[0].professor.id;
  } else {
    localStorage["detailId"] = checkoutEntitySelect.value;
  }
  checkoutEntityButton.setAttribute('href', "detail_professor.html")
}

function checkoutStudentFunction() {
  localStorage["detailId"] = checkoutEntitySelect.value;
  checkoutEntityButton.setAttribute('href', "detail_student.html")
}

function checkoutCourseFunction() {
  localStorage["detailId"] = checkoutEntitySelect.value;
  checkoutEntityButton.setAttribute('href', "detail_course.html")
}


function fillSelectOptionsCustomContextMenu(list) {
  $('#checkoutEntitySelect').find('option').remove();

  let temp;
  temp = $table.bootstrapTable('getData')[0][list];
  console.log(temp)

  // Adding the newly-fetched values
  for (let i = 0; i < temp.length; i++) {
    var option = temp[i];
    var element = document.createElement("option");
    element.textContent = temp[i].heading == null ? onePersonPerColumnFormatter(option).replace("<br/>", " ") : oneStudyPerColumnFormatter(option);
    element.value = option.id;
    console.log(option.id)
    checkoutEntitySelect.appendChild(element);
  }
}