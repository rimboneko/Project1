//let button = document.getElementById("accinf").addEventListener('submit', getAccInf);

let container = document.getElementById('test');

async function getAccInf(e){
	
	e.preventDefault();
	let res = await fetch('http://localhost:8080/project1/api/userinf');
	let data = await res.json();
	showInf(data);
}

function showInf(data){
	
		let info = document.createElement('div');
		info.innerHTML = `<label>User_id: ${data.user_id}</label>
						  <p>${data.username}</p>`;
		container.append(info);
	
}