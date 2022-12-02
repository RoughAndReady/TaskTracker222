function renderDrillBlocks() {
    for (let i = 0; i < drills.length; i++) {
        renderDrill(drills[i]);
    }
}


function renderDrill(drill) {
    let blocks = null;

    for (let col = 0; col <= 2; col++) {
        let startBlockID = getDateOfDrill(drill) + "-" + getStartTimeOfDrill(drill) + (col === 0 ? "" : "-" + col);
        let endBlockID = getDateOfDrill(drill) + "-" + getEndTimeOfDrill(drill) + (col === 0 ? "" : "-" + col);

        console.log(startBlockID + "\n" + endBlockID);

        blocks = $('#' + startBlockID).nextUntil('#' + endBlockID).add('#' + startBlockID);

        let isConcurrent = false;
        blocks.each(function () {
            if(this.value > -1 && this.value !== drill.id){
                isConcurrent = true;
            }
        });

        if (!isConcurrent) {
            break;
        }
    }


    blocks.css("background-color", drill.color);

    blocks.each(function () {
        this.value = drill.id;
    });

    blocks.unbind();
    blocks.click(function () {
        selectDrill(drill.id);
    });

    if (selected_drill.id === drill.id) {
        blocks.css("opacity", "1.0");
    } else {
        blocks.css("opacity", "0.5");
    }
}

function getCompleteDateOfDrill(drill) {
    return drill.date.substring(0, 10);
}

function getDateOfDrill(drill) {
    return drill.date.substring(8, 10);
}

function getDayOfDrill(drill) {
    return daysOfWeek[datesOfWeek.indexOf(parseInt(getDateOfDrill(drill)))];
}

function getStartTimeOfDrill(drill) {
    return drill.startTime.substring(11, 16).replace(":", "");
}

function getStartTimeOfDrillWithColon(drill) {
    return drill.startTime.substring(11, 16);
}

function getEndTimeOfDrill(drill) {
    return drill.endTime.substring(11, 16).replace(":", "");
}

function getEndTimeOfDrillWithColon(drill) {
    return drill.endTime.substring(11, 16);
}

