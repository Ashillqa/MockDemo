document.getElementById('postData').addEventListener('submit', postData);

function postData(event){
    event.preventDefault();

    let pName = document.getElementById('name').value;
    let goal = document.getElementById('goals').value;
    let ass = document.getElementById('assists').value;
    let tackles = document.getElementById('tackles').value;


    fetch('http://localhost:8080/createPlayer', {
        method: 'POST',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({name:pName,goals:goal,assists:ass,tackles:tackles})
    }).then((res) => res.json())
        .then((data) =>  console.log(data)).then(function () {
        document.getElementById('message').innerHTML=`we have created ${pName} as a player`;
    }).catch((err)=>console.log(err))
}

function relU() {
    location.reload();
}