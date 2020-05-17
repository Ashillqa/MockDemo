document.getElementById('LogData').addEventListener('submit',Lfun);
document.getElementById('UData').addEventListener('submit',checker1);
let unsLen,i,x,y
let uns = [];
let pswds =[];
let id_s = [];
unsLen = 0;
pswLen = 0;



function redir(){
    if(unsLen===0){
        document.getElementById('messing').innerHTML = ` Create a user`;
    }else {
        for (i = 0; i < unsLen; i++) {
            if (uns[i] === x && pswds[i] === y) {
                outcome();
            } else {
                document.getElementById('messing').innerHTML = ` Create a user`;
            }
        }
    }
}

function Lfun(event) {

    event.preventDefault();
    x = document.getElementById('useName').value;
    y = document.getElementById('pwd').value;

    fetch('http://localhost:8080/getAllUsers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id, username, password} = user;
                id_s.push(`${id}`)
                uns.push(`${username}`);
                unsLen=uns.length;
                pswds.push(`${password}`);
            });
        }).then(redir)
}

function outcome(){
    let url= 'http://localhost:8080/player.html';
    document.write("Redirecting to the url in 3 seconds...");
    setTimeout(function(){window.location = url;}, 3000);
}

//////////// creating a USER ////////////////////////////////////////////////////////////////////////////////////////////////////////
function crUser() {
    fetch('http://localhost:8080/createUser', {
        method: 'POST',
        headers : {'Content-Type': 'application/json'},
        body:JSON.stringify({username:x,password:y})
    }).then((res) => res.json())
        .then((data) =>  console.log(data))
        .catch((err)=>console.log(err))

    document.getElementById('listFun').innerHTML=`we have created ${x} as a User`;
}

function checker1(event){
    event.preventDefault();
    x = document.getElementById('cuName').value;
    y = document.getElementById('cPwd').value;

    fetch('http://localhost:8080/getAllUsers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id, username, password} = user;
                id_s.push(`${id}`)
                uns.push(`${username}`);
                unsLen=uns.length;
                pswds.push(`${password}`);
            });
        }).then(checker2)
}

function checker2(){
    if(unsLen===0){
        crUser();
    }else {
        for (i = 0; i < unsLen; i++) {
            if (uns[i] === x && pswds[i] === y) {
                document.getElementById('listFun').innerHTML = `User ${x} Exists`
            } else {
                crUser();
                break;
            }
        }
    }
}