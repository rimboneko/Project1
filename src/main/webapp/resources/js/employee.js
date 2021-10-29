//let button = document.getElementById("accinf").addEventListener('submit', getAccInf);

let container = document.getElementById('test');
var user_id;

var sheet = document.createElement('style');
sheet.innerHTML = `label{width: 30%;padding: 15px;margin: 5px 0 22px 0;display: inline-block;border: none;background: #f1f1f1;}
					#test{margin-top:20px;}`;
document.body.appendChild(sheet);

async function getAccInf(e){
	
	e.preventDefault();
	let req = await fetch('http://localhost:8080/project1/api/userinf');
	let data = await req.json();
	user_id = `${data.user_id}`;
	//console.log(user_id);
	showInf(data);
}

function showInf(data){
	
		let info = document.createElement('div');
		info.innerHTML = `<h3>User_id: </h3>
						  <label>${data.user_id}</label><br>
						  <h3>Username: </h3>
						  <label>${data.username}</label><br>
						  <h3>First Name: </h3>
						  <label>${data.first_name}</label><br>
						  <h3>Last Name: </h3>
						  <label>${data.last_name}</label><br>
						  <h3>Email: </h3>
						  <label>${data.email}</label>`;
		container.append(info);
	
}

function reimbForm(e) {
	e.preventDefault();
	let form = document.createElement('form');
	form.innerHTML = `<label for="amount"><b>Amount</b></label><br>
    				  <input type="text" placeholder="Enter the Amount" name="amount" id="amount" required><br>

    				<label for="subdate"><b>Date of submission</b></label><br>
    				<input type="text" placeholder="yyyy-mm-dd" name="subdate" id="subdate" required><br>

    				<label for="description"><b>Description</b></label><br>
    				<input type="text" placeholder="Description" name="description" id="description"><br>
    		
    				<label for="receipt"><b>Do you have a receipt?</b></label><br>
    				<select name="receipt" id="receipt" class="receipt">
    					<option value="true">True</option>
    					<option value="false">False</option>
    				</select><br>
    		
    				<label for="type"><b>Type</b></label><br>
    				<select name="type" id="type" class="type">
    					<option value="1">Lodging</option>
    					<option value="2">Travel</option>
    					<option value="3">Food</option>
    					<option value="4">Other</option>
    				</select><br>
    				
    				<button type="submit" class="submit" onclick="createreimb(event)">Submit</button>`;				
    container.append(form);
}

async function createreimb(e) {
	
	e.preventDefault();
	
	let amount = document.getElementById("amount").value;
	let subdate = document.getElementById("subdate").value;
	let description = document.getElementById("description").value;
	let receipt = document.getElementById("receipt").value;
	let type = document.getElementById("type").value;
	let author = user_id;
	
	let reimb = {
		amount,
		subdate,
		description,
		receipt,
		author,
		type	
	}
	
	console.log(reimb);
	
	try{
		let req = await fetch('http://localhost:8080/project1/api/createreimb', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reimb)
		});
	} catch(e){
		alert('Unsuccessfully');
		return;
	}
	
}