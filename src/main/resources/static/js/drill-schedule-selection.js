let drills = null;
let selected_drill = null;

let users = null;


function selectInitialDrill(){
    if(drills.length > 0){
        selectDrill(drills[0].id);
    }
}

function selectDrill(id){
    selected_drill = findDrillById(id);

    updateInnerText("drill-title", selected_drill.title);

    updateInnerText("drill-date",
        getDayOfDrill(selected_drill) + ", " +
        getCompleteDateOfDrill(selected_drill))

    updateInnerText("drill-time",
        getStartTimeOfDrillWithColon(selected_drill) + " - " +
        getEndTimeOfDrillWithColon(selected_drill));

    updateInnerText("drill-location", selected_drill.location);

    let reportToUser = findUserByID(selected_drill.reportToID);
    if(reportToUser !== null) {
        updateInnerText("drill-report-to",
            reportToUser.firstName + " " + reportToUser.lastName);
    }
    else{
        updateInnerText("drill-report-to", "");
    }

    updateInnerText("drill-participants",
        getListWithSpaces(selected_drill.participants));

    updateInnerText("drill-description", selected_drill.description);

    renderDrillBlocks();
}

function updateInnerText(elementID, newValue) {
    if (newValue !== null && newValue !== undefined) {
        document.getElementById(elementID).innerText = newValue;
    } else {
        document.getElementById(elementID).innerText = "";
    }
}

function getListWithSpaces(list){
    let out = "";

    for(let i = 0; i < list.length; i++){
        out += list[i];
        if(i < list.length - 1){
            out += ", ";
        }
    }

    return out;
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

function findUserByID(id) {
    if (users !== null) {
        for (let index in users) {
            if(users[index].id === id){
                return users[index];
            }
        }
        console.log("Unable to find user - id not found");
    } else {
        console.log("Unable to find user - user list is empty");
    }

    return null;
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