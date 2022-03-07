// Function to build the table
function buildTable($table, sourceData, modelColumns, windowScrollPosition) {
    // Check responseHandler table option?

    // Resetting all the local storage items
    localStorage['history'] = JSON.stringify([sourceData]);
    localStorage['undoChain'] = 1;
    localStorage['lastDoAction'] = 'undo';


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
                "table-striped",
                "table-hover"
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

            if (modelColumns[i].field.endsWith("Degree")) {
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
            //addrbar: true,
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
                /* buttonTest: {
                    'text': 'xxx',
                    'icon': 'bi-arrow-counterclockwise',
                    'attributes': {
                        'title': 'xxxxxx'
                    }
                }, */
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
                //, advancedSearch: 'bi-search'
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

                    windowScrollPosition = 0;
                }

                /* setTimeout(function () { */
                /* }, 2000); */
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

                    windowScrollPosition = 0;
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
                $(window).scrollTop(windowScrollPosition)
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
            //stickyHeaderOffsetLeft: parseInt($('body').css('padding-left'), 10),
            //stickyHeaderOffsetRight: parseInt($('body').css('padding-right'), 10),

            // Advanced search extension
            //advancedSearch: true,
            //idTable: 'advancedTable',

            // Filter Control extension
            //filterControl: true,
            //showFilterControlSwitch: true,
            //filterControlVisible: false,

            // Fixed columns extension
            //fixedColumns: true,
            //fixedNumber: 4,

            // Export extension
            showExport: true,
            exportTypes: ['excel', 'json', 'sql', 'pdf', 'png'],

            keyEvents: true
        });

    }


    $table.bootstrapTable('load', sourceData);



    /* function toggleUndoButtonFunction() {
        console.log(JSON.stringify($table.bootstrapTable('getOptions').buttons.buttonUndo))

        let temp = { ...$table.bootstrapTable('getOptions').buttons };

        if (temp.buttonUndo.attributes.disabled == true) {
            delete temp.buttonUndo.attributes.disabled;
        } else {
            temp.buttonUndo.attributes.disabled = true;
        }

        console.log(JSON.stringify(temp.buttonUndo))

        $table.bootstrapTable('refreshOptions', { buttons: temp });

        console.log(JSON.stringify($table.bootstrapTable('getOptions').buttons.buttonUndo));
    }

    function toggleRedoButtonFunction() {
        let temp = { ...$table.bootstrapTable('getOptions').buttons };

        if (temp.buttonRedo.attributes.disabled == true) {
            delete temp.buttonRedo.attributes.disabled;
        } else {
            temp.buttonRedo.attributes.disabled = true;
        }

        $table.bootstrapTable('refreshOptions', { buttons: temp });
    } */



    function nestedObjectStudySorter(fieldA, fieldB) {
        if (fieldA.heading < fieldB.heading) {
            return -1;
        }
        if (fieldA.heading > fieldB.heading) {
            return 1;
        }
        return 0;
    }

    function nestedObjectPersonSorter(fieldA, fieldB) {
        if (fieldA.lastName < fieldB.lastName) {
            return -1;
        }
        if (fieldA.lastName > fieldB.lastName) {
            return 1;
        }
        return 0;
    }


    function updateScrollPosition() {
        windowScrollPosition = $(window).scrollTop()

        //var tableScrollPosition = $table.bootstrapTable('getScrollPosition')
        //$table.bootstrapTable('scrollTo', tableScrollPosition)
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
        // Fetching data from local storage
        var history = JSON.parse(localStorage["history"]);
        let undoChain = localStorage["undoChain"];


        let tempCustomSourceData = $table.bootstrapTable('getSelections').slice();

        // Inert if no row or all rows selected
        if (tempCustomSourceData.length == 0 || tempCustomSourceData.length == $table.bootstrapTable('getData').length) {
            return;
        }

        // Opens up a new tab if only one row selected
        else if (tempCustomSourceData.length == 1) {
            localStorage["modelColumns"] = JSON.stringify(modelColumns);
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

        // Reset of the local storage units that keep track of changes
        // Because the new table should not show these
        localStorage.removeItem("lastEditedRows");
        localStorage.removeItem("lastAddedRows");

        // Loading new data into the table
        history.push(tempCustomSourceData);
        $table.bootstrapTable('load', tempCustomSourceData);
        $table.bootstrapTable('uncheckAll');

        // Toggling buttons
        disableBundleButtonFunction();
        enableUndoButtonFunction();
        disableRedoButtonFunction();


        // Updating data in local storage
        localStorage["history"] = JSON.stringify(history);
        localStorage["undoChain"] = undoChain;
    }


    function undoFunction() {
        // Fetching data from local storage
        var history = JSON.parse(localStorage["history"]);
        let undoChain = localStorage["undoChain"];
        let lastDoAction = localStorage["lastDoAction"];


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


        // Updating data in local storage
        localStorage["history"] = JSON.stringify(history);
        localStorage["undoChain"] = undoChain;
        localStorage["lastDoAction"] = lastDoAction;
    }


    function redoFunction() {
        // Fetching data from local storage
        var history = JSON.parse(localStorage["history"]);
        let undoChain = localStorage["undoChain"];
        let lastDoAction = localStorage["lastDoAction"];


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


        // Updating data in local storage
        localStorage["history"] = JSON.stringify(history);
        localStorage["undoChain"] = undoChain;
        localStorage["lastDoAction"] = lastDoAction;
    }



    // Keyboard shortcut for bundle function
    $(document).keypress(function (e) {
        if (e.which == 13) {
            bundleFunction();
        }
    });

}
