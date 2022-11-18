let drills = null;
let selected_drill_id = -1;
let selected_drill = null;

/* variables from example */
let events = [];
const colors = [
    "red",
    "green",
    "blue",
    "yellow",
    "purple",
    "cyan",
    "gray",
    "limegreen",
    "pink",
    "orange",
    "magenta"
];
let businessStartHours = 6;
let timeslotInterval = 15;
let eventContainer = document.getElementsByClassName("event-container")[0];
const daysInaWeek = 7;
let sections = daysInaWeek;
let eventsByDay = {};
// let eventDate = "";
let start  = "";
let end  = "";
let id = 1;



function selectInitialDrill(){
    updateSelectedDrill(drills[0].id);
}

function updateSelectedDrill(id) {
    selected_drill_id = id;
    selected_drill = findDrillById(selected_drill_id);

    // updateFieldValue("id", selected_drill.id);
    updateFieldValue("drillTitle", selected_drill.title);
    updateFieldValue("date", selected_drill.date.substring(0,10));
    updateFieldValue("start_time", selected_drill.start_time.substring(11,16));
    updateFieldValue("end_time", selected_drill.end_time.substring(11,16));

    updateFieldValue("location", selected_drill.location);
    updateFieldValue("admin_name", selected_drill.admin_name);
    updateFieldValue("participants", selected_drill.participants);
    updateFieldValue("description", selected_drill.description);
    // updateFieldValue("created_timestamp", selected_drill.created_timestamp);

/* copied from another file; currently not used
    if (users !== null) {
        for (let index in users) {
            if (users[index].id === selected_user_id) {
                document.getElementById('user-list-row-' + users[index].id).className = "user-table-row-selected";
            }
            else{
                document.getElementById('user-list-row-' + users[index].id).className = "user-table-row";
            }
        }
    }
*/

    preloadEvents();
}

function updateFieldValue(elementID, newValue){
    console.log("Updating value for " + elementID + " to " + newValue);
    if(newValue !== null && newValue !== undefined){
        document.getElementById(elementID).innerHTML = newValue;
    }
    else{
        document.getElementById(elementID).innerHTML = "";
    }
}

function findDrillById(id) {
    if (drills !== null) {
        for (let index in drills) {
            console.log(drills[index].id);
            if(drills[index].id === id){
                console.log("Drill found: " + id);
                return drills[index];
            }
        }
        console.log("Unable to find drill - id not found");
    } else {
        console.log("Unable to find drill - drill list is empty");
    }

    return null;
}


/* DEPRECATED: test event functions before changes to support concurrent event display
function clicked_on1(){
    document.getElementById('title').innerHTML = 'Event 1';
    document.getElementById('time').innerHTML = 'Mon 9:00am - 11:00am';
    document.getElementById('place').innerHTML = 'Place 1';
    document.getElementById('admin').innerHTML = 'Person 1';
    document.getElementById('group').innerHTML = 'Group 1';
}

function clicked_on2(){
    document.getElementById('title').innerHTML = 'Event 2';
    document.getElementById('time').innerHTML = 'Thu 12:00pm - 3:00pm';
    document.getElementById('place').innerHTML = 'Place 2';
    document.getElementById('admin').innerHTML = 'Person 2';
    document.getElementById('group').innerHTML = 'Group 2';
}
*/

/* code below is reference from online for concurrent event display */
 function preloadEvents() {
   const evt = {
     id: selected_drill.id,
     starttime: selected_drill.start_time.substring(11,16),
     endtime: selected_drill.end_time.substring(11,16),
     date: selected_drill.date.substring(0,10),
     name: selected_drill.drill_name
   };

   id++;
   eventContainer.innerHTML = "";
   events = [];
   events.push(evt);
   processEvents();
   loadEvents();
 };

 function processEvents() {
   events.forEach(evt => {
     const cell = getCell(evt.starttime);

     // check if exist
     if (!eventsByDay[evt.date]) {
       eventsByDay[evt.date] = {};
       eventsByDay[evt.date][cell] = [];
     }

     if (!eventsByDay[evt.date][cell]) {
       eventsByDay[evt.date][cell] = [];
     }

     eventsByDay[evt.date][cell].push(evt);
   });
 }

 function getCell(starttime) {
   const h = +starttime.split(":")[0];
   return h - businessStartHours;
 }
 /**
  * sort by starttime
  */
 function sortcomparer(e1, e2) {
   const t1start = timeFromString(e1.starttime);
   const t1end = timeFromString(e1.endtime);
   const t2start = timeFromString(e2.starttime);
   const t2end = timeFromString(e2.endtime);

   //return t1start.getTime() - t2start.getTime();
   const t1 = +(t1end - t1start);
   const t2 = +(t2end - t2start);

   return t2 - t1;
 }

 function loadEvents() {
   Object.keys(eventsByDay).forEach(e => {
     const eventsForThisDay = eventsByDay[e];
     Object.keys(eventsForThisDay).forEach(c => {
       const events = eventsForThisDay[c];
       events.sort(sortcomparer);
       var totalEventsPerCell = events.length;
       var offset = 0;

       for (var i = 0; i < events.length; i++) {
         var event = events[i];

         const colPos = getColumnPosition(event.date);
         const perc = 100 / (sections + 1 - colPos);
         const percW = Math.floor(perc / totalEventsPerCell);

         var wMultiplier = 1.5;
         // for last one is just percW
         if (offset === totalEventsPerCell - 1) {
           wMultiplier = 0.95;
         }

         event["width"] = percW * wMultiplier;
         event["left"] = percW * offset;
         event["time"] = `${event.starttime} - ${event.endtime}`;
         renderEvent(event);

         ++offset;
       }
     });
   });
 }

 function renderEvent(evt) {
   var oneEvent = document.createElement("div");
   const color = Math.floor(Math.random() * colors.length);
   oneEvent.setAttribute("class", "slot");

   /**
    * if two events have same start time
    */
   oneEvent.style.background = colors[color];
   oneEvent.style.width = evt.width + "%";
   oneEvent.style.left = evt.left + "%";
   oneEvent.style.zIndex = evt.zindex;
   oneEvent.style.height = getHeight(evt.starttime, evt.endtime) + "px";
   // 100 / ((8-colPos))
   oneEvent.style.gridColumnStart = getColumnPosition(evt.date);
   oneEvent.style.gridRowStart = getRowPosition(evt.starttime);

   /* add to event container */
   eventContainer.appendChild(oneEvent);
 }


 /* UNUSED from example

 function getEventsForCell(starttime) {
   const h = starttime.split(":")[0];
   const eventsForCell = events.filter(e => e.starttime.split(":")[0] === h);
   return eventsForCell;
 }

 function getEventsForDay(day) {
   const eventsForDay = events.filter(e => e.date === day);
   return eventsForDay;
 }
 */


 /**
  * given a start date returns the column position
  *
  * input: startdate (yy-mm-dd)
  */
 function getColumnPosition(startdate) {
   const y = +startdate.split("-")[0];
   const m = +startdate.split("-")[1];
   const d = +startdate.split("-")[2];

   const date = new Date(y, m - 1, d);
   return date.getDay() + 1;
 }

 /**
  * given start time returns the row position
  *
  * input: starttime (x:xx)
  */
 function getRowPosition(starttime) {
   const h = +starttime.split(":")[0];
   const m = +starttime.split(":")[1];
   const totalMinutes = Math.abs(businessStartHours - h) * 60 + m;
   const rowPos = totalMinutes / timeslotInterval + 1;

   return rowPos;
 }

 /**
  * given start and end times will return the height in px
  *
  * start: x:xx
  * end: x:xx
  */
 function getHeight(starttime, endtime) {
   const starthour = starttime.split(":")[0];
   const startmin = starttime.split(":")[1];
   const endhour = endtime.split(":")[0];
   const endmin = endtime.split(":")[1];

   var datestart = new Date();
   var dateend = new Date();
   datestart.setHours(parseInt(starthour));
   datestart.setMinutes(parseInt(startmin));

   dateend.setHours(parseInt(endhour));
   dateend.setMinutes(parseInt(endmin));

   var duration = Math.abs(datestart.valueOf() - dateend.valueOf()) / 1000;
   return duration / 60;
 }

 function timeFromString(string, local) {
   var d = new Date(0);

   var h = string.split(":")[0];
   var m = string.split(":")[1];

   d.setHours(h);
   d.setMinutes(m);

   return d;
 }

 /**
  *  fmt: "yyyy-mm-dd"
  */
 function dateFromString(string, local) {
   var d = new Date(0);

   const y = +string.split("-")[0];
   const m = +string.split("-")[1];
   const day = +string.split("-")[2];

   d.setFullYear(y, m, d);
   d.setMonth(m);
   d.setDate(day);

   return d;
 }