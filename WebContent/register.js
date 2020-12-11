const hideElements = () => {
    document.getElementById("passwordMatchSpan").style.display = 'none';
    document.getElementById('submit').disabled = true
}

const arePasswordsMatching = () => {
    var pass1 = document.getElementById('password').value
    var pass2 = document.getElementById('confirm_password').value
    var email = document.getElementById('Email').value

    document.getElementById("pass2Label").style.display = 'none';

    if (pass1 === pass2) {
        document.getElementById("passwordMatchSpan").style.display = 'none';

        if (email !== '') {
            document.getElementById('submit').disabled = false
        }
    }
    else {
        document.getElementById("passwordMatchSpan").style.display = '';
        document.getElementById('submit').disabled = true
    }
}

const addLabel = () => {
    var email = document.getElementById('Email').value
    var user = document.getElementById('username').value
    var pass = document.getElementById('password').value
    var pass2 = document.getElementById('confirm_password').value
    


    if (email === '') {
        document.getElementById("emailLabel").style.display = '';
    }

    if (pass === '') {
        document.getElementById("pass1Label").style.display = '';
    }

    if (pass2 === '') {
        document.getElementById("pass2Label").style.display = '';
    }

    if (user === '') {
        document.getElementById("usernameLabel").style.display = '';
    }
}

const hideLabelEmail = () => {
    document.getElementById("emailLabel").style.display = 'none';
}

const hideLablePass = () => {
    document.getElementById("pass1Label").style.display = 'none';
}

const hideLableUser = () => {
    document.getElementById("usernameLabel").style.display = 'none';
}

window.onload = hideElements;