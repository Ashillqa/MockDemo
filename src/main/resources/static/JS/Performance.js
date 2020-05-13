function getPerfData() {
    fetch('http://localhost:8080/getAllCats')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id,level} = user;
                document.getElementById('resultSect').innerHTML += `<div>
                    <h2> Level : ${level}</h2>
                    <p id="C${id}"></p>
                
            </div>` ;
            });
        }).then(getPlayer2)
}

function getPlayer2() {
    fetch('http://localhost:8080/getAllPlayers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {name,goals} = user;
                if(`${goals}`<3){ document.getElementById('C1').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }else if (`${goals}`<5){
                    document.getElementById('C2').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }else{
                    document.getElementById('C3').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }

            });

        })

}