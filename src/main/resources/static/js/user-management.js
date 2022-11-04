let users = null;
let selected_user_id = -1;
let selected_user = null;


function userManagementSetup() {
}

function selectInitialUser(){
    updateSelectedUser(users[0].id);
}

function updateSelectedUser(id) {
    selected_user_id = id;

    selected_user = findUserById(selected_user_id);

    updateFieldValue("input-first_name", selected_user.firstName);
    updateFieldValue("input-last_name", selected_user.lastName);
    // updateFieldValue("input-account_email", selected_user.email);
    updateFieldValue("input-mil_email", selected_user.militaryEmail);
    updateFieldValue("input-civ_email", selected_user.civilianEmail);
    updateFieldValue("input-personal_phone", selected_user.phoneNumber);
    updateFieldValue("input-office_phone", selected_user.officeNumber);
    updateFieldValue("input-rank", selected_user.rank);
    updateFieldValue("input-workcenter", selected_user.workCenter);
    updateFieldValue("input-flight", selected_user.flight);
    updateFieldValue("input-teams", selected_user.teams);
    updateFieldValue("input-id", selected_user.id);

    $('select').val(selected_user.teams).trigger('chosen:updated');

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

}

function updateFieldValue(elementID, newValue){
    if(newValue !== null && newValue !== undefined){
        document.getElementById(elementID).value = newValue;
    }
    else{
        document.getElementById(elementID).value = "";
    }
}

function findUserById(id) {
    if (users !== null) {
        for (let index in users) {
            // console.log(users[index].id);
            if(users[index].id === id){
                // console.log("User found: " + id);
                return users[index];
            }
        }
        console.log("Unable to find user - id not found");
    } else {
        console.log("Unable to find user - user list is empty");
    }

    return null;
}