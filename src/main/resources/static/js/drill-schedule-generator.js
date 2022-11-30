let calendarStartHour = 5;
let calendarEndHour = 22;
let daysOfWeek = [];
let datesOfWeek = [];
let concurrencyMatrix = [];
let numberOfColumns = null;

function generateDayAndDateHeaders(){
    generateDatesFromDays(daysOfWeek, datesOfWeek);

    generateDayHeaders(datesOfWeek);
    generateDayHeaders(daysOfWeek);
}

function generateDatesFromDays(days, dates){
    let datesOut = [days.length];

    let dayIndex = dates.length - 1;
    for(let day = days.length - 1; day >= 0; day--){
        datesOut[day] = dates[dayIndex];

        if(!days[day].includes("1") && !days[day].includes("2")){
            dayIndex--;
        }
    }

    datesOfWeek = datesOut;
}

function generateDayHeaders(days){
    numberOfColumns = daysOfWeek.length;

    let dayHeader = document.createElement('div');
    dayHeader.className = "dayHeaders";

    let blankDay = document.createElement('span');
    blankDay.className = "dayHeader";
    blankDay.innerText = "";
    dayHeader.appendChild(blankDay);

    for(let day = 0; day < days.length; day++){
        let daySpan = document.createElement('span');
        daySpan.className = "dayHeader";
        daySpan.innerText = days[day];

        dayHeader.appendChild(daySpan);
    }

    let calendarContainer = document.getElementById("calendar-container");
    calendarContainer.appendChild(dayHeader);
}

function generateTimeColumn(){
    let timeColDiv = document.createElement('div');
    timeColDiv.className = "timeCol";

    for(let hour = calendarStartHour; hour <= calendarEndHour; hour++){
        let timeDiv = document.createElement('div');
        timeDiv.className = "timeDiv";

        let timeText = document.createElement('span');
        timeText.className = "timeText";
        timeText.innerHTML = (hour < 10 ? "0" : "") + hour + ":00";

        timeDiv.appendChild(timeText);
        timeColDiv.appendChild(timeDiv);
    }

    let calendarView = document.getElementById("calendar-view");
    calendarView.appendChild(timeColDiv);
}

function generateDayColumns(){
    let concurrencyLevel = 0;
    let lastDate = -1;

    for(let i = 0; i < datesOfWeek.length; i++){
        if(lastDate === datesOfWeek[i]){
            concurrencyLevel++;
        } else {
            concurrencyLevel = 0;
        }

        generateDayColumn(datesOfWeek[i], concurrencyLevel);

        lastDate = datesOfWeek[i];
    }
}

function generateDayColumn(date, concurrencyLevel){
    let dayCol = document.createElement('div');
    dayCol.id = date;
    dayCol.className = "dayCol";

    for(let hour = calendarStartHour; hour <calendarEndHour; hour++){
        for(let section = 1; section <= 4; section++){
            let box = document.createElement('div');
            box.className = "q" + section;
            box.id = date + "-" + (hour < 10 ? "0" : "") + hour +
                ((15 * (section - 1)) === 0 ? "00" : (15 * (section - 1))) +
                (concurrencyLevel > 0 ? "-" + concurrencyLevel : "");
            box.value = -1;

            dayCol.appendChild(box);
        }
    }

    let calendarView = document.getElementById("calendar-view");
    calendarView.appendChild(dayCol);
}