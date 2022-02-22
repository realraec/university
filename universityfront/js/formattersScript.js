function applyFormatter(modelColumns, i) {
    let temp = modelColumns[i].field
    if (temp == 'minorDegree' || temp == 'majorDegree') {
        modelColumns[i].formatter = degreeFormatter;
    } else if (temp == 'courses') {
        modelColumns[i].formatter = coursesFormatter;
        modelColumns[i].class = 'text-nowrap';
        modelColumns[i].sortable = false;
    } else if (temp == 'professor') {
        modelColumns[i].formatter = professorFormatter;
    } else if (temp == 'students') {
        modelColumns[i].formatter = studentsFormatter;
        modelColumns[i].class = 'text-nowrap';
        modelColumns[i].sortable = false;
    } else if (temp == 'gender') {
        modelColumns[i].formatter = genderFormatter;
    } else if (temp == 'department') {
        modelColumns[i].formatter = departmentFormatter;
    } else if (temp == 'diploma') {
        modelColumns[i].formatter = diplomaFormatter;
    } else if (temp.startsWith('isExam')) {
        modelColumns[i].formatter = booleanFormatter;
    }
}

// Nested arrays or JSON objects
function degreeFormatter(value, row, index, field) {
    let temp = { ...value }
    return temp.id == null ? null : (temp.heading + " (" + temp.code + ")");
}

function coursesFormatter(value, row, index, field) {
    // Sorting alphabteically by last name
    if (value == null) return;
    value.sort((fieldA, fieldB) => (fieldA.heading > fieldB.heading) ? 1 : ((fieldB.heading > fieldA.heading) ? -1 : 0))

    let finalStringToReturn = "";
    for (let i = 0; i < value.length; i++) {
        let temp = { ...value[i] }
        finalStringToReturn += temp.heading + " (" + temp.code + ")"
        if (i < value.length - 1) {

            if ((i + 1) % 3 != 0) {
                finalStringToReturn += " — "
            } else {
                finalStringToReturn += "<br/>"
            }
        }
    }
    return finalStringToReturn == "" ? null : finalStringToReturn;
}

function professorFormatter(value, row, index, field) {
    let temp = { ...value }
    return temp.id == null ? null : (temp.lastName.toUpperCase() + " " + temp.firstName + "<br/>(" + temp.code + ")");
}

function studentsFormatter(value, row, index, field) {
    // Sorting alphabteically by last name
    if (value == null) return;
    value.sort((fieldA, fieldB) => (fieldA.lastName > fieldB.lastName) ? 1 : ((fieldB.lastName > fieldA.lastName) ? -1 : 0))

    let finalStringToReturn = "";
    for (let i = 0; i < value.length; i++) {
        let temp = { ...value[i] }
        finalStringToReturn += temp.lastName.toUpperCase() + " " + temp.firstName + " (" + temp.code + ")"
        if (i < value.length - 1) {
            if (i % 2 == 0) {
                finalStringToReturn += " — "
            } else {
                finalStringToReturn += "<br>"
            }
        }
    }
    return finalStringToReturn == "" ? null : finalStringToReturn;
}


// Enums
/* function genderFormatter(value, row, index, field) {
    let color;
    let symbol;
    if (value.endsWith("_FEMALE")) {
        color = '#E9967A'
        symbol = 'bi-gender-female'
    } else if (value.endsWith("_MALE")) {
        color = '#BDB76B'
        symbol = 'bi-gender-male'
    } else {
        color = '#8FBC8F'
        symbol = 'bi-gender-ambiguous'
    }
    return '<div style="color: ' + color + '">' + '<i class="bi ' + symbol + '"></i></div>';
} */
function genderFormatter(value, row, index, field) {
    if (value.endsWith("_FEMALE")) {
        return '<i class="bi bi-gender-female me-2"></i>' + "Female";
    } else if (value.endsWith("_MALE")) {
        return '<i class="bi bi-gender-male me-2"></i>' + "Male";
    } else {
        return '<i class="bi bi-gender-ambiguous me-2"></i>' + "Other";
    }
}

function departmentFormatter(value, row, index, field) {
    if (value.endsWith("_HARD_SCIENCES")) {
        return "Hard sciences";
    } else if (value.endsWith("_SOCIAL_SCIENCES")) {
        return "Social sciences";
    } else if (value.endsWith("_ARTS")) {
        return "Arts";
    } else if (value.endsWith("_LANGUAGES")) {
        return "Languages";
    } else {
        return "Misc.";
    }
}

function diplomaFormatter(value, row, index, field) {
    if (value.endsWith("_HIGH_SCHOOL")) {
        return "High school diploma";
    } else if (value.endsWith("_BACHELORS")) {
        return "Bachelor's degree";
    } else if (value.endsWith("_MASTERS")) {
        return "Master's degree";
    } else {
        return "Doctoral degree";
    }
}

function booleanFormatter(value, row, index, field) {
    if (value == false) {
        return '<i class="bi bi-x-circle-fill me-2"></i>No';
    } else {
        return '<i class="bi bi-check-circle me-2"></i>Yes';
    }
}