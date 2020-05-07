const postbtn = document.getElementById('createP');

const sendHttpRequest = (method,url,data) => {
    const promise = new Promise((resolve,reject)=>{
        const xhr = new XMLHttpRequest();
        xhr.open(method,url);
        xhr.responseType = 'json';
        if(data) {
            xhr.setRequestHeader('Content-Type','application/json');
        }
        xhr.onload = () => {
            resolve(xhr.response);
        };

        xhr.send(JSON.stringify(data));
    });
    return promise;
};

const sendData = () => {
    sendHttpRequest('POST','http:localhost:8080/createPlayer',{
        name: document.getElementById('name').value,
        position: document.getElementById('position').value,
        goals: document.getElementById('goals').value,
        assists: document.getElementById('assists').value,
        tackles:document.getElementById('tackles').value
    }).then(responseData =>{
        console.log(responseData);
    });
};
postbtn.addEventListener('click',sendData);