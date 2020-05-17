function getPerfData() {
    fetch('http://localhost:8080/getAllCats')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id,level} = user;
                document.getElementById('resultPerf').innerHTML += `<div>
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
                const {name,goals,assists,tackles} = user;
                if(`${goals}`>`${assists}`&&`${goals}`>=`${tackles}`){ document.getElementById('C3').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }else if (`${assists}`>`${goals}`&&`${assists}`>=`${tackles}`){
                    document.getElementById('C2').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }else if(`${tackles}`>`${goals}`&&`${tackles}`>`${assists}`){
                    document.getElementById('C1').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                } else{
                    document.getElementById('C4').innerHTML += `<div>
                <ul>
                    <li> Name : ${name}</li>
                </ul>
            </div>` ;
                }

            });

        })

}