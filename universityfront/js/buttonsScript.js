$('#buttons-placeholder').load('buttons.html', function () {
  $(this).children(':first').unwrap();
});


function updateHistoryWithChanges(table) {
  // Fetching changed rows
  let newCurrentRows = table.bootstrapTable('getData');

  // Fetching saved history
  var history = JSON.parse(localStorage["history"]);

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
  localStorage["history"] = JSON.stringify(history);
}


async function addStudentFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/addStudent', {
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
      let studentReturned = {
        id: temp.id,
        code: temp.code,
        lastName: temp.lastName,
        firstName: temp.firstName
      }

      // Update the current table adding the student to the array
      for (let index = 0; index < currentRows.length; index++) {
        let studentsSet = [...currentRows[index].students];
        studentsSet.push(studentReturned);

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'students',
          value: studentsSet
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function removeStudentFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/removeStudent', {
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
      let studentReturned = {
        id: temp.id,
        code: temp.code,
        lastName: temp.lastName,
        firstName: temp.firstName
      }

      // Update the current table removing the student from the array
      for (let index = 0; index < currentRows.length; index++) {
        let studentsSet = [...currentRows[index].students];
        studentsSet = studentsSet.filter(function (object) {
          return object.id !== studentReturned.id;
        });

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'students',
          value: studentsSet
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function addCourseFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/degrees/addCourse', {
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
      let courseReturned = {
        id: temp.id,
        code: temp.code,
        heading: temp.heading
      }

      // Update the current table adding the student to the array
      for (let index = 0; index < currentRows.length; index++) {
        let coursesSet = [...currentRows[index].courses];
        coursesSet.push(courseReturned);

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'courses',
          value: coursesSet
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function removeCourseFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/degrees/removeCourse', {
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
      let courseReturned = {
        id: temp.id,
        code: temp.code,
        heading: temp.heading
      }

      // Update the current table removing the course from the array
      for (let index = 0; index < currentRows.length; index++) {
        let coursesSet = [...currentRows[index].courses];
        coursesSet = coursesSet.filter(function (object) {
          return object.id !== courseReturned.id;
        });

        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'courses',
          value: coursesSet
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function setNewProfessorFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }


  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/setNewProfessor', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "professorCode": codeModalInput.value
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
      let temp = data.data.professor;
      let professorReturned = {
        id: temp.id,
        code: temp.code,
        lastName: temp.lastName,
        firstName: temp.firstName
      }

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'professor',
          value: professorReturned
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function setNewMajorDegreeFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }


  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/students/setNewMajorDegree', {
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
      let degreeReturned = {
        id: temp.id,
        code: temp.code,
        heading: temp.heading,
      }

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'majorDegree',
          value: degreeReturned
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function setNewMinorDegreeFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }


  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/students/setNewMinorDegree', {
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
      let degreeReturned = {
        id: temp.id,
        code: temp.code,
        heading: temp.heading,
      }

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'minorDegree',
          value: degreeReturned
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)
    })
};


async function getStudents() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/getStudents/' + currentRowsId.toString(), {
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
      let temp = data.data.students;
      console.log(temp);
      return temp;
    });
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


async function toggleIsExamMadeFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/setIsExamMadeByProfessorMultiple', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "isExamMadeByProfessor": radioModalInputRadio1.checked
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
      console.log(temp)
      /* let professorReturned = {
        id: temp.id,
        code: temp.code,
        lastName: temp.lastName,
        firstName: temp.firstName
      } */

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'isExamMadeByProfessor',
          value: temp
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });
};


async function toggleIsExamTakenFunction() {

  // Getting the ids of the current rows
  let currentRows = $table.bootstrapTable('getData');
  var currentRowsId = [];
  for (let i = 0; i < currentRows.length; i++) {
    currentRowsId.push(currentRows[i].id)
  }

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/courses/setIsExamTakenByStudentsMultiple', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
      "coursesIdList": currentRowsId,
      "isExamTakenByStudents": radioModalInputRadio1.checked
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
      let temp = data.data.boolean;
      /* let professorReturned = {
        id: temp.id,
        code: temp.code,
        lastName: temp.lastName,
        firstName: temp.firstName
      } */

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'isExamTakenByStudents',
          value: temp
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });
}


async function newEntryPersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
    personType = "student";
  } else {
    personType = "professor";
  }

  var history = JSON.parse(localStorage["history"]);

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

      localStorage["history"] = JSON.stringify(history);
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

  var history = JSON.parse(localStorage["history"]);

  // To force the user to wait for the response
  disableButtonsInModal();

  // Performing the operation
  const response = await fetch('http://localhost:8080/' + studyType + 's/create', {
    method: 'put',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
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

      localStorage["history"] = JSON.stringify(history);
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


async function promotePersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
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
  const response = await fetch('http://localhost:8080/persons/promote', {
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

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });

};


async function demotePersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
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
  const response = await fetch('http://localhost:8080/persons/demote', {
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

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });
}


async function giveWarningPersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
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
  const response = await fetch('http://localhost:8080/persons/giveWarning', {
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
          value: temp[index]
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });

};


async function kickOutPersonFunction() {

  let personType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
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
  const response = await fetch('http://localhost:8080/persons/kickOut', {
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
      let temp = data.data.maxWarning;

      // Update the current table with the changed value returned
      for (let index = 0; index < currentRows.length; index++) {
        $table.bootstrapTable('updateCell', {
          index: index,
          field: 'warnings',
          value: temp
        })
      }

      // Update history with the changes made
      updateHistoryWithChanges($table)

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

      // Update history with the changes made
      updateHistoryWithChanges($table)

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

      // Update history with the changes made
      updateHistoryWithChanges($table)

    });

};



async function refreshEntryFunction() {

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
    entityType = "S";
  } else if (documentURL.endsWith("lookup_professors.html")) {
    entityType = "P";
  } else if (documentURL.endsWith("lookup_courses.html")) {
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

    });
}


async function deleteEntryFunction() {

  let entityType = "";
  let documentURL = document.URL;
  if (documentURL.endsWith("lookup_students.html")) {
    entityType = "S";
  } else if (documentURL.endsWith("lookup_professors.html")) {
    entityType = "P";
  } else if (documentURL.endsWith("lookup_courses.html")) {
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
  const response = await fetch('http://localhost:8080/entities/delete', {
    method: 'delete',
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


      // Update history with the changes made
      var history = JSON.parse(localStorage["history"]);

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

      localStorage["history"] = JSON.stringify(history);

      $table.bootstrapTable('load', [])

    });

}
