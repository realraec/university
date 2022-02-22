// Function to build the table
function buildSimpleTable($table, url, modelColumns) {

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
        url: url,

        // Columns and classes first for the architecture while loading
        columns: modelColumns,
        classes: [
            "table",
            "table-bordered",
            "table-striped",
            "table-hover"
        ]
    });

}


document.addEventListener('visibilitychange', function () {
    if (this.visibilityState == "visible") {
        localStorage["detailId"] = $table.bootstrapTable('getData')[0].id;
    }
});
