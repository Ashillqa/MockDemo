function updP(id, name){
    let goal = document.getElementById(`U${id}`).value

    fetch('http://localhost:8080/updatePlayer/'+id, {
        method: 'PUT',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({id:id,name:name,goals:goal})
    }).then((res) => res.json())
        .then((data) =>  console.log(data)).then(relU)
        .catch((err)=>console.log(err))
}


function relU() {
    location.reload();
}