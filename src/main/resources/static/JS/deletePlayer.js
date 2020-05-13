function Delfunc(id) {
    fetch('http://localhost:8080/deletePlayer/'+id,{
        method: 'DELETE'
    }).then(res => res.text())
        .then(res => console.log(res)).then(rel)
}
function rel() {
    location.reload();
}