function details() {
    fetch('http://localhost:8080/getAllUsers')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            data.forEach((user) => {
                const {id, username, password} = user;
                document.getElementById('resultUser').innerHTML += `<div>
                <ul>
                    <li> Hello ${username}</li>
                    <li>New Password<span><input id="N${id}" type="password" pattern="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required autocomplete="off"><button onclick="updUser(${id},'${username}','${password}')">Update</button></span></li>
                     <button onclick="delUser(${id})">DeleteAccount</button> 
                </ul>                 
            </div>` ;
            });
        })
}

