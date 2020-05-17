

function updUser(id,username) {
    let newP = document.getElementById(`N${id}`).value;
    fetch('http://localhost:8080/updateUser/'+id, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({id:id,username:username, password:newP})
    }).then((res) => res.json())
        .then((data) => console.log(data)).then(LogAgain)
        .catch((err) => console.log(err))
}


function LogAgain() {
    let url= 'http://localhost:8080/index.html';
    setTimeout(function(){window.location = url;}, 2000);
}