$('#buttons-placeholder').load('buttons.html', function () {
  $(this).children(':first').unwrap();


  const buttons = document.getElementById('buttons');

  const contactButtons = document.getElementById('contactButtons');
  const degreesButtons = document.getElementById('degreesButtons');
  const levelButtons = document.getElementById('levelButtons');
  const sanctionButtons = document.getElementById('sanctionButtons');
  const rewardButtons = document.getElementById('rewardButtons');
  const misc1Buttons = document.getElementById('misc1Buttons');
  const misc2Buttons = document.getElementById('misc2Buttons');
  const TBDButtons = document.getElementById('TBDButtons');
  const extraButtons = document.getElementById('extraButtons');
  const studentsButtons = document.getElementById('studentsButtons');
  const examsButtons = document.getElementById('examsButtons');
  const coursesButtons = document.getElementById('coursesButtons');

  // PERSONS
  const promoteButton = document.getElementById('promoteButton');

  // STUDENTS
  const setNewMinorDegreeButton = document.getElementById('setNewMinorDegreeButton');
  const setNewMajorDegreeButton = document.getElementById('setNewMajorDegreeButton');



  // ALL
  const newEntryButton = document.getElementById('newEntryButton');
  const deleteButton = document.getElementById('deleteButton');

  // COURSES
  const setNewProfessorButton = document.getElementById('setNewProfessorButton');
  const toggleIsExamMadeButton = document.getElementById('toggleIsExamMadeButton');
  const toggleIsExamTakenButton = document.getElementById('toggleIsExamTakenButton');
  
  const testButtonABC = document.getElementById('testButtonABC');
  //testButtonABC.addEventListener('click', getStudents)
  

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

async function getDepartments() {

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

      for (let i = 0; i < temp.length; i++) {
        var option = temp[i];
        var element = document.createElement("option");
        element.textContent = option;
        element.value = option;
        selectModalInput.appendChild(element)
      }

    });
};




// MULTIPLE
// PUT - ...FORM...
//toggleIsExamMadeButton.addEventListener('click', toggleIsExamMadeFunction);
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


// MULTIPLE
// PUT - ...FORM...
//toggleIsExamTakenButton.addEventListener('click', toggleIsExamTakenFunction)
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
