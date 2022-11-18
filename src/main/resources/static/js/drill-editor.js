let drill = null;
let editing = false;
let customLocationRequested = false;

function initDrillEditor() {
    if (editing) {
        populateFieldsWithExistingData();
    }
}

function showCustomDrillInputField() {
    if (!customLocationRequested) {
        console.log("Showing custom location input");
        document.getElementById("custom-drill-input").style.visibility = "visible";
        document.getElementById("custom-drill-input").style.position = "relative";

        try {
            document.getElementById("drill_location_chosen").children[0].style.backgroundColor = "#e0e0e0";
        } catch (e) {}

        customLocationRequested = true;
    }
}

function hideCustomDrillInputField() {
    if (customLocationRequested) {
        console.log("Hiding custom location input");
        document.getElementById("custom-drill-input").style.visibility = "hidden";
        document.getElementById("custom-drill-input").style.position = "absolute";
        updateFieldValue("custom-drill-input", null);

        try {
            document.getElementById("drill_location_chosen").children[0].style.backgroundColor = "unset";
        } catch (e) {}

        customLocationRequested = false;
    }
}

function populateFieldsWithExistingData() {
    updateFieldValue("drill-title", drill.title);
    updateFieldValue("drill-color", drill.color);
    updateFieldValue("drill-date", drill.date.substring(0,10));
    updateFieldValue("drill-start-time", drill.startTime.substring(11,16));
    updateFieldValue("drill-end-time", drill.endTime.substring(11,16));
    updateLocationValue();

    updateFieldValue("add-drill-officers", drill.officerName);
    updateFieldValue("add-drill-participants", drill.participants);

    updateFieldValue("drill-description", drill.description);

    updateFieldValue("drill-id", drill.id);
}

function updateFieldValue(elementID, newValue) {
    if (newValue !== null && newValue !== undefined) {
        document.getElementById(elementID).value = newValue;
    } else {
        document.getElementById(elementID).value = "";
    }
}

function updateLocationValue(){
    let exists = false;
    $('#drill-location  option').each(function(){
        if (this.value == drill.location) {
            exists = true;
        }
    });

    if(exists){
        updateFieldValue("drill-location", drill.location);
    }
    else{
        console.log("Using custom location");
        updateFieldValue("drill-location", "Enter custom location");
        updateFieldValue("custom-drill-input", drill.location);
        showCustomDrillInputField();
    }
}