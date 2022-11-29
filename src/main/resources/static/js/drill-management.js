let preselect_id = -1;
let customLocationRequested = false;

let drills = null;
let selected_drill_id = -1;
let selected_drill = null;

let users = null;

function drillManagementSetup(){
}

function selectInitialDrill(){
    if(preselect_id !== -1){
        updateSelectedDrill(preselect_id);
    }
    else {
        updateSelectedDrill(drills[0].id);
    }
}

function updateSelectedDrill(id){
    selected_drill_id = id;

    selected_drill = findDrillById(id);

    populateFieldsWithExistingData();
}

function showCustomDrillInputField() {
    if (!customLocationRequested) {
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
    if(selected_drill.id >= 0) {
        updateAllFields()
    }
    else if(selected_drill.id === -2){
        clearAllFields();
    }

    updateFieldValue("drill-id", selected_drill.id);

    if (drills !== null) {
        for (let index in drills) {
            if (drills[index].id === selected_drill_id) {
                document.getElementById('drill-list-row-' + drills[index].id).className = "drill-table-row-selected";
            }
            else{
                document.getElementById('drill-list-row-' + drills[index].id).className = "drill-table-row";
            }
        }
    }
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
        if (this.value == selected_drill.location) {
            exists = true;
        }
    });

    if(exists){
        $('#drill-location').val(selected_drill.location).trigger('chosen:updated');
        hideCustomDrillInputField();
    }
    else{
        $('#drill-location').val("Custom location").trigger('chosen:updated');
        updateFieldValue("custom-drill-input", selected_drill.location);
        showCustomDrillInputField();
    }
}

function findDrillById(id) {
    if (drills !== null) {
        for (let index in drills) {
            // console.log(drills[index].id);
            if(drills[index].id === id){
                // console.log("Drill found: " + id);
                return drills[index];
            }
        }
        console.log("Unable to find drill - id not found");
    } else {
        console.log("Unable to find drill - drill list is empty");
    }

    return null;
}

function findUserByID() {
    if (users !== null) {
        for (let index in users) {
            // console.log(drills[index].id);
            if(users[index].id === id){
                // console.log("Drill found: " + id);
                return users[index];
            }
        }
        console.log("Unable to find user - id not found");
    } else {
        console.log("Unable to find user - user list is empty");
    }

    return null;
}

function updateAllFields(){
    document.getElementById("drill-date").type = "date";

    updateFieldValue("drill-title", selected_drill.title);
    updateFieldValue("drill-color", selected_drill.color);
    updateFieldValue("drill-date", selected_drill.date.substring(0, 10));
    updateFieldValue("drill-start-time", selected_drill.startTime.substring(11, 16));
    updateFieldValue("drill-end-time", selected_drill.endTime.substring(11, 16));
    updateFieldValue("drill-description", selected_drill.description);

    updateLocationValue();

    $('#drill-report-to').val(selected_drill.reportToID).trigger('chosen:updated');
    $('#drill-participants').val(selected_drill.participants).trigger('chosen:updated');
}

function clearAllFields(){
    document.getElementById("drill-date").type = "text";

    updateFieldValue("drill-title", null);
    updateFieldValue("drill-color", -1);
    updateFieldValue("drill-date", null);
    updateFieldValue("drill-start-time", null);
    updateFieldValue("drill-end-time", null);
    updateFieldValue("drill-description", null);

    $('#drill-location').val(selected_drill.location).trigger('chosen:updated');
    hideCustomDrillInputField();

    $('#drill-report-to').val(-1).trigger('chosen:updated');
    $('#drill-participants').val(null).trigger('chosen:updated');
}

function findNextWeek(selectedDate){
    let nextWeek = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), (selectedDate.getDate() + 8));
    return formatDateForSelector(nextWeek);
}

function findLastWeek(selectedDate){
    let lastWeek = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), (selectedDate.getDate() - 6));
    return formatDateForSelector(lastWeek);
}

function formatDateForSelector(dateToFormat){
    let out = dateToFormat.getFullYear() + '-' +
        (dateToFormat.getMonth() + 1 < 10 ? '0' : '') + (dateToFormat.getMonth() + 1) + '-' +
        (dateToFormat.getDate() < 10 ? '0' : '') + dateToFormat.getDate();
    return out;
}