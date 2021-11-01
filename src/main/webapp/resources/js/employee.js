var user_id;
var username ;
var info = true;
var sub = true;
var pend = true;
var reso = true;
var sheet = document.createElement('style');
sheet.innerHTML = `table {
  							font-family: arial, sans-serif;
  							border-collapse: collapse;
  							width: 100%;
							}
					td, th {
 							 border: 1px solid #dddddd;
 							 text-align: left;
  							 padding: 8px;
							}
					tr:nth-child(even) {
 							 background-color: #dddddd;
											}
					input[type=text] {
							  width: 28%;
							  padding: 15px;
							  margin: 5px 0 22px 0;
							  display: inline-block;
							  border: none;
							  background: #f1f1f1;
										}
					input[type=text]:focus {
							  background-color: #ddd;
							  outline: none;
					  						}
					.submit {
							  background-color: #04AA6D;
							  color: white;
							  padding: 16px 20px;
							  margin: 8px 0;
							  border: none;
							  cursor: pointer;
							  width: 30%;
							  opacity: 0.9;
							}

					.submit:hover {
					 		opacity:1;
					}
					.type {
							  width: 30%;
							  padding: 15px;
							  margin: 5px 0 22px 0;
							  display: inline-block;
							  border: none;
							  background: #f1f1f1;
							}
					.info {
							  width: 28%;
							  padding: 15px;
							  margin: 5px 0 22px 0;
							  display: inline-block;
							  border: none;
							  background: #f1f1f1;
										}
					.info:focus {
							  background-color: skyblue;
							  outline: none;
					  						}`;
document.body.appendChild(sheet);

async function getAccInf(e){
	while(info) {	
	e.preventDefault();
	let req = await fetch('http://localhost:8080/project1/api/userinf');
	let data = await req.json();
	user_id = `${data.user_id}`;
	username = `${data.username}`;
	showInf(data);
	info = false;
	}	
}

function showInf(data){
	let container = document.getElementById('accinfo');
	let info = document.createElement('div');
		info.innerHTML = `<h3>User_id: </h3>
						  <label class="info">${data.user_id}</label><br>
						  <h3>Username: </h3>
						  <label class="info">${data.username}</label><br>
						  <h3>First Name: </h3>
						  <label class="info">${data.first_name}</label><br>
						  <h3>Last Name: </h3>
						  <label class="info">${data.last_name}</label><br>
						  <h3>Email: </h3>
						  <label class="info">${data.email}</label>`;
		container.append(info);
	
}

function reimbForm() {
	while(sub) {
	let form = document.createElement('form');
	let container = document.getElementById('createreimb');
	form.innerHTML = `<label for="amount"><b>Amount</b></label><br>
    				  <input type="text" placeholder="Enter the Amount" name="amount" id="amount" required><br>

    				<label for="subdate"><b>Date of submission</b></label><br>
    				<input type="text" placeholder="yyyy-mm-dd" name="subdate" id="subdate" required><br>

    				<label for="description"><b>Description</b></label><br>
    				<input type="text" placeholder="Description" name="description" id="description"><br>
    		
    				<label for="receipt"><b>Do you have a receipt?</b></label><br>
    				<select name="receipt" id="receipt" class="type">
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
    sub = false;
	}
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

function openTab(evt, tabName) {
  // Declare all variables
  var i, tabcontent, tablinks;
  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
  switch(tabName) {
  case"home":
  	break;
  case "accinfo":
  	getAccInf(evt);
  	break;
  case "createreimb":
  	reimbForm(evt);
  	break;
  case "pendreimb":
  	pendTable(evt);
  	break;
  case "resreimb":
  	resTable(evt);
  	break;
  }
}

async function pendTable(e) {
	while(pend) {
	e.preventDefault();
	let user = {username};
	
	try{
		let req = await fetch('http://localhost:8080/project1/api/pendreimb', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		
		let res= await req.json();
		viewreq(res, 'pendreimb');
		pend = false;
	} catch(e){
		alert('No pending reimbursement');
		return;
	}

	}
}

async function resTable(e) {
	
	while(reso) {
	e.preventDefault();
	let user = {username};
	
	try{
		let req = await fetch('http://localhost:8080/project1/api/resreimb', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		
		let res= await req.json();
		viewreq(res, 'resreimb');
		reso = false;
	} catch(e){
		alert('No resolving reimbursement');
		return;
		}
	}
}

function logout() {
  window.localStorage.clear();
  window.location.reload(true);
  window.location.replace('../html/login.html');
}

function viewreq(data, tab){
	var container = document.getElementById(tab);
	var table = document.createElement('table');
	var row = document.createElement('tr');
	
	row.innerHTML = `<th>Reimb_id</th>
					 <th>Reimb_amount</th>
					 <th>Reimb_submitted</th>
					 <th>Reimb_resolved</th>
					 <th>Reimb_description</th>
					 <th>Reimb_receipt</th>
					 <th>Reimb_author</th>
					 <th>Reimb_resolver</th>
					 <th>Reimb_status</th>
					 <th>Reimb_type</th>`;	
	table.append(row);					
	container.append(table);
	
	for(reqObj of data) {
		row = document.createElement('tr');
	
		row.innerHTML = `<td>${reqObj.reimb_id}</td>
						 <td>${reqObj.amount}</td>
						 <td>${reqObj.submitted}</td>
						 <td>${reqObj.resolved}</td>
						 <td>${reqObj.description}</td>
						 <td>${reqObj.receipt}</td>
						 <td>${reqObj.author_id}</td>
						 <td>${reqObj.resolver_id}</td>
						 <td>${reqObj.status_id}</td>
						 <td>${reqObj.type_id}</td>`;
		table.append(row);
		container.append(table);
	}

}