// Function to build the table
function buildTable($table, sourceData, modelColumns, windowScrollPosition) {

    // Resetting all the session storage items
    sessionStorage.setItem('history', JSON.stringify([sourceData]));
    sessionStorage.setItem('undoChain', 1)
    sessionStorage.setItem('lastDoAction', 'undo');


    // One-line table
    if (sourceData.length == 1) {
        // Making the row uncheckable
        modelColumns.shift();

        // Tweaks to columns
        for (let i = 0; i < modelColumns.length; i++) {

            // Making the columns no longer sortable
            modelColumns[i].sortable = false;

            // Applying the formatters to the right indexes
            applyFormatter(modelColumns, i)
        }


        $table.bootstrapTable({
            // Columns and classes first for the architecture while loading
            columns: modelColumns,
            classes: [
                "table",
                "table-bordered",
                "table-striped"
            ]
        });


        // Multi-line table
    } else {

        // Making the rows checkable
        var checkboxColumn = { field: 'state', checkbox: true, printIgnore: true };
        modelColumns.unshift(checkboxColumn);

        // Tweaks to columns
        for (let i = 1; i < modelColumns.length; i++) {

            // Making the columns sortable (except for the first one)
            modelColumns[i].sortable = true;

            if (modelColumns[i].field.endsWith("egree")) {
                modelColumns[i].sorter = nestedObjectStudySorter;
            } else if (modelColumns[i].field == "professor") {
                modelColumns[i].sorter = nestedObjectPersonSorter;
            }

            // Applying the formatters to the right indexes
            applyFormatter(modelColumns, i)
        }


        $table.bootstrapTable({

            // Columns and classes first for the architecture while loading
            columns: modelColumns,
            classes: [
                "table",
                "table-bordered",
                "table-striped",
                "table-hover"
            ],


            sortName: 'id',
            sortOrder: 'asc',
            maintainMetaData: true,
            showPaginationSwitch: true,
            clickToSelect: true,
            multipleSelectRow: true,


            // Default and custom buttonbar items
            search: true,
            searchAccentNeutralise: true,
            showColumns: true,
            showSearchClearButton: true,
            buttons: {
                buttonUndo: {
                    'text': 'Undo',
                    'icon': 'bi-arrow-counterclockwise',
                    'event': undoFunction,
                    'attributes': {
                        'title': 'Undo previous table generation',
                        'disabled': true
                    }
                },
                buttonRedo: {
                    'text': 'Redo',
                    'icon': 'bi-arrow-clockwise',
                    'event': redoFunction,
                    'attributes': {
                        'title': 'Redo next table generation',
                        'disabled': true
                    }
                },
                buttonBundle: {
                    'text': 'Bundle',
                    'icon': 'bi-boxes',
                    'event': bundleFunction,
                    'attributes': {
                        'title': 'New table from selected rows',
                        'disabled': true
                    }
                }
            },
            buttonsOrder: ['filterControlSwitch', 'paginationSwitch', 'print', 'export', 'buttonUndo', 'buttonRedo', 'buttonBundle', 'columns'],
            icons: {
                columns: 'bi-list-ul',
                print: 'bi-printer',
                filterControlSwitchShow: 'bi-search',
                filterControlSwitchHide: 'bi-search',
                clearSearch: 'bi-trash',
                export: 'bi-download',
                paginationSwitchDown: 'bi-caret-down-square',
                paginationSwitchUp: 'bi-caret-up-square'
            },


            // Events
            onColumnSwitch: function (field, checked) {
                // For hidden columns to not be printed
                let temp = { ...$table.bootstrapTable('getOptions').columns };
                let result = temp[0].find(obj => {
                    return obj.field === field
                })
                temp[0][result.fieldIndex].printIgnore = (checked == 0 ? true : false);
                $table.bootstrapTable('refreshOptions', { columns: temp });
            },
            onCheck: function (row, $element) {
                let numberCheckedElements = $table.bootstrapTable('getSelections').length;

                if (numberCheckedElements == 1) {
                    enableBundleButtonFunction();
                } else {

                    let numberElements = $table.bootstrapTable('getData').length;
                    if (numberCheckedElements == numberElements) {
                        disableBundleButtonFunction();
                    }
                }
            },
            onUncheck: function (row, $element) {
                let numberCheckedElements = $table.bootstrapTable('getSelections').length;

                if (numberCheckedElements == 0) {
                    disableBundleButtonFunction();
                } else {

                    let numberElements = $table.bootstrapTable('getData').length;
                    if (numberCheckedElements != numberElements) {
                        enableBundleButtonFunction();
                    }
                }
            },
            onCheckAll: function () {
                let numberCheckedElements = $table.bootstrapTable('getSelections').length;
                let numberElements = $table.bootstrapTable('getData').length;

                if (numberCheckedElements == numberElements) {
                    disableBundleButtonFunction();
                } else {
                    enableBundleButtonFunction();
                }
            },
            onUncheckAll: function () {
                let numberCheckedElements = $table.bootstrapTable('getSelections').length;
                let numberElements = $table.bootstrapTable('getData').length;

                if (numberCheckedElements == 0) {
                    disableBundleButtonFunction();
                } else {
                    enableBundleButtonFunction();
                }
            },
            onRefreshOptions: updateScrollPosition,
            onPostBody: function () {
                // Fix to the "scrolling back to top" issue, check GitHub for updates
                //$(window).scrollTop(windowScrollPosition);
                //$('html,body').animate({ scrollTop: windowScrollPosition }, 1);
                window.scrollTo({
                    top: windowScrollPosition,
                    //left: 0,
                    behavior: 'instant',
                });
            },


            // Custom error message
            formatNoMatches: function () {
                return 'No matching records found, try again.';
            },


            // Extensions
            // Print extension
            showPrint: true,

            // Sticky Header extension
            stickyHeader: true,

            // Export extension
            showExport: true,
            exportTypes: ['excel', 'json', 'sql', 'pdf', 'png'],

            // Key events extensions
            keyEvents: true
        });

    }

    $table.bootstrapTable('load', sourceData);


    // Keyboard shortcut for bundle function
    $(document).keypress(function (e) {
        if (e.which == 13) {
            bundleFunction();
        }
    });


    function updateScrollPosition() {
        windowScrollPosition = $(window).scrollTop();
        //var tableScrollPosition = $table.bootstrapTable('getScrollPosition')
        //$table.bootstrapTable('scrollTo', tableScrollPosition)
    }
}


function nestedObjectStudySorter(fieldA, fieldB) {
    if (fieldA == null || fieldB == null) {
        if (fieldA == null) {
            return 1
        } else {
            return -1
        }
    }
    
    if (fieldA.heading < fieldB.heading) {
        return -1;
    }
    if (fieldA.heading > fieldB.heading) {
        return 1;
    }
    return 0;
}

function nestedObjectPersonSorter(fieldA, fieldB) {
    if (fieldA == null || fieldB == null) {
        if (fieldA == null) {
            return 1
        } else {
            return -1
        }
    }

    if (fieldA.lastName < fieldB.lastName) {
        return -1;
    }
    if (fieldA.lastName > fieldB.lastName) {
        return 1;
    }
    return 0;
}


function enableBundleButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    delete temp.buttonBundle.attributes.disabled;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}

function disableBundleButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    temp.buttonBundle.attributes.disabled = true;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}

function enableUndoButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    delete temp.buttonUndo.attributes.disabled;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}

function disableUndoButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    temp.buttonUndo.attributes.disabled = true;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}

function enableRedoButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    delete temp.buttonRedo.attributes.disabled;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}

function disableRedoButtonFunction() {
    let temp = { ...$table.bootstrapTable('getOptions').buttons };
    temp.buttonRedo.attributes.disabled = true;
    $table.bootstrapTable('refreshOptions', { buttons: temp });
}


function bundleFunction() {
    // Fetching data from session storage
    var history = JSON.parse(sessionStorage.getItem('history'))
    let undoChain = sessionStorage.getItem('undoChain')

    let tempCustomSourceData = $table.bootstrapTable('getSelections').slice();

    // Inert if no row or all rows selected
    if (tempCustomSourceData.length == 0 || tempCustomSourceData.length == $table.bootstrapTable('getData').length) {
        return;
    }

    // Opens up a new tab if only one row selected
    else if (tempCustomSourceData.length == 1) {
        localStorage["detailId"] = JSON.stringify(tempCustomSourceData[0].id);

        /* let temp = tempCustomSourceData[0].lastName.toUpperCase() + " " + tempCustomSourceData[0].firstName;
        localStorage.setItem("userFullname", temp);
        localStorage["userType"] = tempCustomSourceData[0].phone; */

        let documentURL = document.URL;
        if (documentURL.endsWith("lookup_students.html")) {
            window.open("detail_student.html", "_blank")
        } else if (documentURL.endsWith("lookup_professors.html")) {
            window.open("detail_professor.html", "_blank")
        } else if (documentURL.endsWith("lookup_courses.html")) {
            window.open("detail_course.html", "_blank")
        } else if (documentURL.endsWith("lookup_degrees.html")) {
            window.open("detail_degree.html", "_blank")
        }
        return;
    }

    // Removing elements in the history if called after an undo
    if (undoChain != 0) {
        let tempChain = undoChain;
        for (i = 0; i < tempChain - 1; i++) {
            history.pop();
            undoChain--;
        }
    }

    // Loading new data into the table
    history.push(tempCustomSourceData);
    $table.bootstrapTable('load', tempCustomSourceData);
    $table.bootstrapTable('uncheckAll');

    // Toggling buttons
    disableBundleButtonFunction();
    enableUndoButtonFunction();
    disableRedoButtonFunction();


    // Updating data in session storage
    sessionStorage.setItem('history', JSON.stringify(history));
    sessionStorage.setItem('undoChain', undoChain);
}


function undoFunction() {
    // Fetching data from session storage
    var history = JSON.parse(sessionStorage.getItem('history'))
    let undoChain = sessionStorage.getItem('undoChain')
    var lastDoAction = sessionStorage.getItem('lastDoAction')


    // Inert if nothing to undo
    if (undoChain == history.length) {
        return;
    }

    // To avoid having to click twice
    if (lastDoAction == 'redo') {
        undoChain++
        lastDoAction = 'undo';
    }

    // Going back once
    console.log(history)
    $table.bootstrapTable('load', history[history.length - 1 - undoChain])
    undoChain++;


    // Toggling buttons
    enableRedoButtonFunction();
    if (undoChain == history.length) {
        disableUndoButtonFunction();
    }

    $table.bootstrapTable('uncheckAll');


    // Updating data in session storage
    sessionStorage.setItem('history', JSON.stringify(history));
    sessionStorage.setItem('undoChain', undoChain);
    sessionStorage.setItem('lastDoAction', lastDoAction);
}


function redoFunction() {
    // Fetching data from session storage
    var history = JSON.parse(sessionStorage.getItem('history'))
    let undoChain = sessionStorage.getItem('undoChain')
    var lastDoAction = sessionStorage.getItem('lastDoAction')

    // Inert if nothing to redo
    if (undoChain == 0 || (undoChain == 1 && lastDoAction == 'undo')) {
        return;
    }

    // To avoid having to click twice
    if (lastDoAction == 'undo') {
        undoChain--
        lastDoAction = 'redo';
    }

    // Going forth once
    undoChain--;
    $table.bootstrapTable('load', history[history.length - 1 - undoChain])

    // Toggling buttons
    enableUndoButtonFunction();
    if (undoChain == 0) {
        disableRedoButtonFunction();
    }


    // Updating data in session storage
    sessionStorage.setItem('history', JSON.stringify(history));
    sessionStorage.setItem('undoChain', undoChain);
    sessionStorage.setItem('lastDoAction', lastDoAction);
}


function customContextMenu(selectQuery) {

    $(selectQuery)
        //$("td:nth-of-type(6)")
        //$("td").eq(6)
        .on('contextmenu', function (e) {
            console.log(e.currentTarget)

            // Use the default context menu if the input/td is empty
            if (e.target.innerText == "-" || e.currentTarget.value == "") {
                return true;
            }

            console.log($('#professorInput'))
            $('td, input, textarea').css({ 'box-shadow': 'none', 'background-color': '#f2f2f2' });
            var top = e.pageY - 10;
            var left = e.pageX - 120;
            $(this).css('box-shadow', 'inset 1px 1px 0px 0px red, inset -1px -1px 0px 0px red');
            $("#customContextMenu").css({
                display: "block",
                top: top,
                left: left
            });

            var cellIndex = e.target.cellIndex
            var inputId = e.currentTarget.id;
            //alert(cell.cellIndex + ' : ' + cell.parentNode.rowIndex);
            //sessionStorage.setItem('cellIndex', cellIndex)
            //console.log(cellIndex)
            console.log(e.currentTarget.value)

            let documentURL = document.URL;

            if (documentURL.includes("student")) {
                if (cellIndex == 7 || inputId == "coursesInput") {
                    checkoutEntitySelect.removeAttribute("hidden");
                    fillSelectOptionsCustomContextMenu("courses");
                    checkoutEntityButton.addEventListener('click', checkoutCourseFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutDegreeFunction, false)
                } else {
                    checkoutEntitySelect.setAttribute("hidden", "");
                    checkoutEntityButton.addEventListener('click', checkoutDegreeFunction(e));
                    checkoutEntityButton.removeEventListener('click', checkoutCourseFunction, false)
                }
            } else if (documentURL.includes("professor")) {
                checkoutEntitySelect.removeAttribute("hidden");
                fillSelectOptionsCustomContextMenu("courses");
                checkoutEntityButton.addEventListener('click', checkoutCourseFunction);
            } else if (documentURL.includes("course")) {
                if (cellIndex == 5 || inputId == "professorInput") {
                    checkoutEntitySelect.setAttribute("hidden", "");
                    checkoutEntityButton.addEventListener('click', checkoutProfessorFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutDegreeFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutStudentFunction, false)
                } else if (cellIndex == 6 || inputId == "studentsInput") {
                    checkoutEntitySelect.removeAttribute("hidden");
                    fillSelectOptionsCustomContextMenu("students");
                    checkoutEntityButton.addEventListener('click', checkoutStudentFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutProfessorFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutDegreeFunction, false)
                } else {
                    checkoutEntitySelect.setAttribute("hidden", "");
                    checkoutEntityButton.addEventListener('click', checkoutDegreeFunction(e));
                    checkoutEntityButton.removeEventListener('click', checkoutProfessorFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutStudentFunction, false)
                }
            } else if (documentURL.includes("degree")) {
                checkoutEntitySelect.removeAttribute("hidden");
                if (cellIndex == 3 || inputId == "coursesInput") {
                    fillSelectOptionsCustomContextMenu("courses");
                    checkoutEntityButton.addEventListener('click', checkoutCourseFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutProfessorFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutStudentFunction, false)
                } else if (cellIndex == 4 || inputId == "professorsInput") {
                    fillSelectOptionsCustomContextMenu("professors");
                    checkoutEntityButton.addEventListener('click', checkoutProfessorFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutCourseFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutStudentFunction, false)
                } else {
                    fillSelectOptionsCustomContextMenu("students");
                    checkoutEntityButton.addEventListener('click', checkoutStudentFunction);
                    checkoutEntityButton.removeEventListener('click', checkoutCourseFunction, false)
                    checkoutEntityButton.removeEventListener('click', checkoutProfessorFunction, false)
                }
            }

            return false; //blocks default Webbrowser right click menu
        });

    $("body").on("click", function (e) {
        if ($("#customContextMenu").css('display') == 'block' && e.target.tagName != "SELECT") {
            console.log(e.target.tagName)
            $("#customContextMenu").hide();
        }
        $('td, input, textarea').css({ 'box-shadow': 'none', 'background-color': '#f2f2f2' });
    });

    $("#customContextMenu a").on("click", function () {
        $(this).parent().hide();
    });
}
