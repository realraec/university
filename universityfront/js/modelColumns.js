// Columns
var modelColumnsStudent =
    [
        {
            field: 'id',
            title: 'ID',
            //visible: false,
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'code',
            title: 'Uni-ID&trade;',
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'lastName',
            title: 'Last name',
            filterControl: 'input',
            class: 'text-nowrap',
        }, {
            field: 'firstName',
            title: 'First name',
            filterControl: 'input',
            class: 'text-nowrap'
        }, {
            field: 'gender',
            title: 'Gender',
            filterControl: 'select',
            class: 'text-nowrap'
        }, {
            field: 'level',
            title: 'Level',
            filterControl: 'select'
        }, {
            field: 'majorDegree',
            title: 'Major degree',
            filterControl: 'select'
        }, {
            field: 'minorDegree',
            title: 'Minor degree',
            filterControl: 'select'
        }, {
            field: 'courses',
            title: 'Enrolled in courses',
            visible: false,
            filterControl: 'input'
        }, {
            field: 'birthdate',
            title: 'Birthdate',
            class: 'text-nowrap',
            filterControl: 'input'
        }, {
            field: 'email',
            title: 'E-mail',
            class: 'text-nowrap',
            filterControl: 'input'
        }, {
            field: 'phone',
            title: 'Phone',
            filterControl: 'input'
        }, {
            field: 'totalTuition',
            title: 'Tuition',
            filterControl: 'input'
        }, {
            field: 'balance',
            title: 'Balance',
            filterControl: 'input'
        }, {
            field: 'credits',
            title: 'Credits',
            filterControl: 'select'
        }, {
            field: 'diploma',
            title: 'Last diploma',
            class: 'text-nowrap',
            filterControl: 'select'
        }, {
            field: 'warnings',
            title: '<i class="bi bi-exclamation-triangle-fill"></i>',
            filterControl: 'select'
        }
    ]


var modelColumnsProfessor =
    [
        {
            field: 'id',
            title: 'ID',
            //visible: false,
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'code',
            title: 'Uni-ID&trade;',
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'lastName',
            title: 'Last name',
            filterControl: 'input',
            class: 'text-nowrap',
        }, {
            field: 'firstName',
            title: 'First name',
            class: 'text-nowrap',
            filterControl: 'input'
        }, {
            field: 'gender',
            title: 'Gender',
            class: 'text-nowrap',
            filterControl: 'select'
        }, {
            field: 'level',
            title: 'Level',
            filterControl: 'select'
        }, {
            field: 'courses',
            title: 'Teaching courses',
            visible: false,
            filterControl: 'input'
        }, {
            field: 'birthdate',
            title: 'Birthdate',
            class: 'text-nowrap',
            filterControl: 'input'
        }, {
            field: 'email',
            title: 'E-mail',
            class: 'text-nowrap',
            filterControl: 'input'
        }, {
            field: 'phone',
            title: 'Phone',
            filterControl: 'input'
        }, {
            field: 'salary',
            title: 'Salary',
            filterControl: 'select'
        }, {
            field: 'balance',
            title: 'Balance',
            filterControl: 'input'
        }, {
            field: 'warnings',
            title: '<i class="bi bi-exclamation-triangle-fill"></i>',
            filterControl: 'select'
        }
    ]


var modelColumnsCourse =
    [
        {
            field: 'id',
            title: 'ID',
            //visible: false,
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'code',
            title: 'Code',
            filterControl: 'input'
        }, {
            field: 'heading',
            title: 'Heading',
            filterControl: 'input'
        }, {
            field: 'department',
            title: 'Department',
            filterControl: 'select'
        }, {
            field: 'isExamMadeByProfessor',
            title: 'Exam made',
            filterControl: 'select'
        }, {
            field: 'isExamTakenByStudents',
            title: 'Exam taken',
            filterControl: 'select'
        }, {
            field: 'professor',
            title: 'Professor',
            filterControl: 'select'
        }, {
            field: 'degree',
            title: 'Part of degree',
            visible: false,
            filterControl: 'input'
        }, {
            field: 'students',
            title: 'Students enrolled',
            filterControl: 'input'
        }
    ]


var modelColumnsDegree =
    [
        {
            field: 'id',
            title: 'ID',
            //visible: false,
            switchable: false,
            filterControl: 'input'
        }, {
            field: 'code',
            title: 'Code',
            filterControl: 'input'
        }, {
            field: 'heading',
            title: 'Heading',
            filterControl: 'input'
        }, {
            field: 'department',
            title: 'Department',
            filterControl: 'select'
        }, {
            field: 'professors',
            title: 'Professors teaching',
            visible: false,
            class: 'text-nowrap',
            filterControl: 'input',
        }, {
            field: 'students',
            title: 'Students enrolled',
            visible: false,
            filterControl: 'input'
        }, {
            field: 'courses',
            title: 'Courses associated',
            class: 'text-nowrap',
            filterControl: 'input'
        }
    ]