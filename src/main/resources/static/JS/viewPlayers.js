function getData() {
    fetch('http://localhost:8080/getAllPlayers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id,name,goals} = user;
                document.getElementById('result').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                    <li> Goals : ${goals} </li>
                    <button onclick="delFunc(${id})">Delete</button><span>
                    <p>Update goals</p><input id="U${id}" type="number" min="0">
                    <button onclick="updP(${id},'${name}')">Update</button></span>
                </ul>
            </div>` ;
            });

        })
}
