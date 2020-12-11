const disableLogin = () => {
    document.getElementById('submit').disabled = true
}

const hideLablePass = () => {
    document.getElementById("pass1Label").style.display = 'none';
}

const hideLableUser = () => {
    document.getElementById("usernameLabel").style.display = 'none';
}

const addLabel = () => {
    var user = document.getElementById('username').value
    var pass = document.getElementById('password').value
    
    if (pass === '') {
        document.getElementById("pass1Label").style.display = '';
    }

    if (user === '') {
        document.getElementById("usernameLabel").style.display = '';
    }

    if (pass !== '' && user !== '') {
        document.getElementById('submit').disabled = false
    }
}

const check = () => {
    var user = document.getElementById('username').value
    var pass = document.getElementById('password').value
    
    if (pass !== '' && user !== '') {
        document.getElementById('submit').disabled = false
    }
}

window.onload = disableLogin;