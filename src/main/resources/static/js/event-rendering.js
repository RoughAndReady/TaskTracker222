function testWorking(){
    alert("JS test");
}

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