// Columns
var modelColumnsStudent =
    [
        {
            field: 'id',
            title: 'ID',
            visible: false,
            switchable: false
        }, {
            field: 'code',
            title: 'Code',
            switchable: false
        }, {
            field: 'lastName',
            title: 'Last name',
            class: 'text-nowrap'
        }, {
            field: 'firstName',
            title: 'First name',
            class: 'text-nowrap'
        }, {
            field: 'gender',
            title: 'Gender',
            class: 'text-nowrap'
        }, {
            field: 'level',
            title: 'Level'
        }, {
            field: 'majorDegree',
            title: 'Major degree'
        }, {
            field: 'minorDegree',
            title: 'Minor degree'
        }, {
            field: 'courses',
            title: 'Enrolled in courses',
            visible: false
        }, {
            field: 'birthdate',
            title: 'Birthdate',
            class: 'text-nowrap'
        }, {
            field: 'email',
            title: 'E-mail',
            class: 'text-nowrap'
        }, {
            field: 'phone',
            title: 'Phone'
        }, {
            field: 'totalTuition',
            title: 'Tuition'
        }, {
            field: 'balance',
            title: 'Balance'
        }, {
            field: 'credits',
            title: 'Credits'
        }, {
            field: 'diploma',
            title: 'Last diploma',
            class: 'text-nowrap'
        }, {
            field: 'warnings',
            title: '<i class="bi bi-exclamation-triangle-fill"></i>'
        }
    ]


var modelColumnsProfessor =
    [
        {
            field: 'id',
            title: 'ID',
            visible: false,
            switchable: false
        }, {
            field: 'code',
            title: 'Code',
            switchable: false
        }, {
            field: 'lastName',
            title: 'Last name',
            class: 'text-nowrap'
        }, {
            field: 'firstName',
            title: 'First name',
            class: 'text-nowrap'
        }, {
            field: 'gender',
            title: 'Gender',
            class: 'text-nowrap'
        }, {
            field: 'level',
            title: 'Level'
        }, {
            field: 'courses',
            title: 'Teaching courses',
            visible: false
        }, {
            field: 'birthdate',
            title: 'Birthdate',
            class: 'text-nowrap'
        }, {
            field: 'email',
            title: 'E-mail',
            class: 'text-nowrap'
        }, {
            field: 'phone',
            title: 'Phone'
        }, {
            field: 'salary',
            title: 'Salary'
        }, {
            field: 'balance',
            title: 'Balance'
        }, {
            field: 'warnings',
            title: '<i class="bi bi-exclamation-triangle-fill"></i>'
        }
    ]


var modelColumnsCourse =
    [
        {
            field: 'id',
            title: 'ID',
            visible: false,
            switchable: false
        }, {
            field: 'code',
            title: 'Code',
            switchable: false
        }, {
            field: 'heading',
            title: 'Heading'
        }, {
            field: 'department',
            title: 'Department'
        }, {
            field: 'isExamMadeByProfessor',
            title: 'Exam made'
        }, {
            field: 'isExamTakenByStudents',
            title: 'Exam taken'
        }, {
            field: 'professor',
            title: 'Professor'
        }, {
            field: 'students',
            title: 'Students enrolled'
        }, {
            field: 'degree',
            title: 'Part of degree',
            visible: false
        }
    ]


var modelColumnsDegree =
    [
        {
            field: 'id',
            title: 'ID',
            visible: false,
            switchable: false
        }, {
            field: 'code',
            title: 'Code',
            switchable: false
        }, {
            field: 'heading',
            title: 'Heading'
        }, {
            field: 'department',
            title: 'Department'
        }, {
            field: 'courses',
            title: 'Courses associated',
            class: 'text-nowrap'
        }, {
            field: 'professors',
            title: 'Professors teaching',
            visible: false,
            class: 'text-nowrap'
        }, {
            field: 'students',
            title: 'Students enrolled',
            visible: false
        }
    ]