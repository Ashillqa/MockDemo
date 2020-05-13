document.getElementById('postData').addEventListener('submit', postData);

function postData(event){
    event.preventDefault();

    let pName = document.getElementById('name').value;
    let goal = document.getElementById('goals').value;


    fetch('http://localhost:8080/createPlayer', {
        method: 'POST',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({name:pName,goals:goal})
    }).then((res) => res.json())
        .then((data) =>  console.log(data))
        .catch((err)=>console.log(err))

    document.getElementById('message').innerHTML=`we have created ${pName} as a player`;
}