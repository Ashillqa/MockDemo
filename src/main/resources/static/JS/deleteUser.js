function delUser(id) {
    fetch('http://localhost:8080/deleteUser/'+id,{
        method: 'DELETE'
    }).then(res => res.text())
        .then(res => console.log(res)).then(redLogin)
}

function redLogin() {
    let url= 'http://localhost:8080/index.html';
    setTimeout(function(){window.location = url;}, 2000);
}

