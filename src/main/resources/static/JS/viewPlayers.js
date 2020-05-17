function getData() {
    fetch('http://localhost:8080/getAllPlayers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id,name,goals,assists,tackles} = user;
                document.getElementById('result').innerHTML += `<div style="float: left">
                <ul>
                    <li> Name : ${name}</li>
                    <li> Goals : ${goals}<span><input id="U${id}" type="number" min="0"><button onclick="updP(${id},'${name}',${assists},${tackles})">Update</button></span> </li>   
                    <li>Assists : ${assists}<span><input id="A${id}" type="number" min="0"><button onclick="updA(${id},'${name}',${goals},${tackles})">Update</button></span></li>
                    <li>Tackles : ${tackles}<span><input id="T${id}" type="number" min="0"><button onclick="updT(${id},'${name}',${goals},${assists})">Update</button></span></li>                              
                    <button onclick="delFunc(${id})">Delete</button>
                </ul>
            </div>` ;
            });

        })
}
