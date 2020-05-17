function updP(id, name,ass,tack){
    let goal = document.getElementById(`U${id}`).value

    fetch('http://localhost:8080/updatePlayer/'+id, {
        method: 'PUT',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({id:id,name:name,goals:goal,assists:ass,tackles:tack})
    }).then((res) => res.json())
        .then((data) =>  console.log(data)).then(relU)
        .catch((err)=>console.log(err))
}

function updA(id,name,goals,tack) {
    let ass = document.getElementById(`A${id}`).value

    fetch('http://localhost:8080/updatePlayer/'+id,{
        method: 'PUT',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({id:id,name:name,goals:goals,assists:ass,tackles:tack})
    }).then((res) => res.json())
        .then((data) =>  console.log(data)).then(relU)
        .catch((err)=>console.log(err))

}
function updT(id,name,goals,ass) {
    let tack = document.getElementById(`T${id}`).value

    fetch('http://localhost:8080/updatePlayer/'+id,{
        method: 'PUT',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({id:id,name:name,goals:goals,assists:ass,tackles:tack})
    }).then((res) => res.json())
        .then((data) =>  console.log(data)).then(relU)
        .catch((err)=>console.log(err))
}


function relU() {
    location.reload();
}