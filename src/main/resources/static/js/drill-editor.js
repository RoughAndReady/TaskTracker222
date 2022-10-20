let drill = null;
let editing = false;
let customLocationRequested = false;

function initDrillEditor() {
    console.log(drill.title);

    if (editing) {
        populateFieldsWithExistingData();
    }
}

function showCustomDrillInputField() {
    if (!customLocationRequested) {
        document.getElementById("custom-drill-input").style.visibility = "visible";
        document.getElementById("custom-drill-input").style.position = "relative";

        customLocationRequested = true;
    }
}

function hideCustomDrillInputField() {
    if (customLocationRequested) {
        document.getElementById("custom-drill-input").style.visibility = "hidden";
        document.getElementById("custom-drill-input").style.position = "absolute";

        customLocationRequested = false;
    }
}

function populateFieldsWithExistingData() {
    updateFieldValue("drill-date", drill.date);
    updateFieldValue("drill-start-time", drill.startTime);
    updateFieldValue("drill-end-time", drill.endTime);

    updateFieldValue("add-drill-officers", drill.officerName);
    updateFieldValue("add-drill-participants", drill.participants);

    updateFieldValue("drill-description", drill.description);
}

function updateFieldValue(elementID, newValue) {
    if (newValue !== null && newValue !== undefined) {
        document.getElementById(elementID).value = newValue;
    } else {
        document.getElementById(elementID).value = "";
    }
}