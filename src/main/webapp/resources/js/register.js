let form = document.getElementById("register").addEventListener('submit', register);

async function register(e){
	
	e.preventDefault();
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let firstname = document.getElementById("firstname").value;
	let lastname = document.getElementById("lastname").value;
	let email = document.getElementById("email").value;
	let role = document.getElementById("role").value;
	
	let user = {
		username,
		password,
		firstname,
		lastname,
		email,
		role
	}
	
	try{
		let req = await fetch('http://localhost:8080/project1/api/register', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		location.href = '../html/login.html';
	} catch(e){
		alert('Username already exist, please choose another username!');
		return;
	}
	
	}