let users = null;
let selected_user_id = -1;
let selected_user = null;


function userManagementSetup() {
}

function selectInitialUser() {
    updateSelectedUser(users[0].id);
}

function updateSelectedUser(id) {
    selected_user_id = id;

    selected_user = findUserById(selected_user_id);

    updateFieldValue("input-first_name", selected_user.firstName);
    updateFieldValue("input-last_name", selected_user.lastName);
    updateFieldValue("input-mil_email", selected_user.militaryEmail);
    updateFieldValue("input-civ_email", selected_user.civilianEmail);
    updateFieldValue("input-personal_phone", selected_user.phoneNumber);
    updateFieldValue("input-office_phone", selected_user.officeNumber);
    updateFieldValue("input-id", selected_user.id);


    $('#input-rank').val(selected_user.rank).trigger('chosen:updated');
    $('#input-flight').val(selected_user.flight).trigger('chosen:updated');
    $('#input-workcenter').val(selected_user.workCenter).trigger('chosen:updated');
    $('#input-teams').val(selected_user.teams).trigger('chosen:updated');
    $('#input-admin').val(selected_user.admin ? "true" : "false").trigger('chosen:updated');

    updateUserApprovedStatus(selected_user.approved);

    if (users !== null) {
        for (let index in users) {
            if (users[index].id === selected_user_id) {
                if (users[index].approved) {
                    document.getElementById('user-list-row-' + users[index].id).className = "user-table-row-selected";
                } else {
                    document.getElementById('user-list-row-' + users[index].id).className = "user-table-row-unapproved-selected";
                }
            } else {
                if (users[index].approved) {
                    document.getElementById('user-list-row-' + users[index].id).className = "user-table-row";
                } else {
                    document.getElementById('user-list-row-' + users[index].id).className = "user-table-row-unapproved";
                }
            }
        }
    }

}

function updateFieldValue(elementID, newValue) {
    // console.log("Updating value for " + elementID + " to " + newValue);
    if (newValue !== null && newValue !== undefined) {
        document.getElementById(elementID).value = newValue;
    } else {
        document.getElementById(elementID).value = "";
    }
}

function findUserById(id) {
    if (users !== null) {
        for (let index in users) {
            // console.log(users[index].id);
            if (users[index].id === id) {
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

function updateUserApprovedStatus(approved) {
    if (approved) {
        document.getElementById("user-submit-button").value = "Submit";
        document.getElementById("user-submit-button").setAttribute("onclick", "return verifyAllFields();");

        document.getElementById("user-delete-button").value = "Delete";
        document.getElementById("user-delete-button").onclick = confirmUserDeletion;
    } else {
        document.getElementById("user-submit-button").value = "Approve";
        document.getElementById("user-submit-button").onclick = confirmUserApprove;

        document.getElementById("user-delete-button").value = "Deny";
        document.getElementById("user-delete-button").onclick = confirmUserDeny;
    }
}

function confirmUserDeletion() {
    return confirm('Are you sure you want to delete this user?');
}

function confirmUserDeny() {
    return confirm('Are you sure you want to deny this user?');
}

function confirmUserApprove() {
    return confirm('Are you sure you want to approve this user?');
}

function changeToUserSubmit() {
    document.getElementById("user-submit-button").value = "Submit";
}

function changeToUserDelete() {
    document.getElementById("user-delete-button").value = "Delete";
}

function verifyAllFields() {
    if ($("#input-rank").val().length === 0) {
        alert("Please select a rank!");
        return false;
    } else if ($("#input-flight").val().length === 0) {
        alert("Please select a flight!");
        return false;
    } else if ($("#input-workcenter").val().length === 0) {
        alert("Please select a workcenter!");
        return false;
    } else if ($("#input-teams").val().length === 0) {
        alert("Please select at least one team!");
        return false;
    } else {
        return true;
    }
}