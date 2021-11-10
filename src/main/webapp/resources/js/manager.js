
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
					.enter {
							  background-color: #04AA6D;
							  color: white;
							  padding: 16px 20px;
							  margin: 8px 0;
							  border: none;
							  cursor: pointer;
							  width: 10%;
							  opacity: 0.9;
							}

					.enter:hover {
					 		opacity:1;
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
					  						}
					 hr {
							  border: 1px solid #f1f1f1;
							  margin-bottom: 25px;
							}`;
document.body.appendChild(sheet);
var emp = true;
var pend = true;
var reso = true;

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
  case "allpendreimb":
  	getAllpend();
  	break;
  case "allresreimb":
  	getAllres();
  	break;
  case "empreimb":
  	break;
  case "allemp":
  	getEmployees();
  	break;
  case "updatestatus":
  	break;
  }
}

async function getReq(e) {
	e.preventDefault();
	let id = document.getElementById("reqid").value;
	let reqid = {id}
	console.log(reqid);
	try{
		let req = await fetch('http://localhost:8080/project1/api/getreimb', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reqid)
		});
		
		let res= await req.json();
		console.log(res);
		showreq(res);
	} catch(e){
		alert('Reimbursement request does not exist!');
		return;
	}
}

function showreq(data){
	let container = document.getElementById('updatestatus');
	let info = document.createElement('div');
		info.innerHTML = `<hr><h3>Reimb_id: </h3>
						  <label id="reimb_id" class="info">${data.reimb_id}</label><br>
						  <h3>amount: </h3>
						  <label class="info">${data.amount}</label><br>
						  <h3>submitted: </h3>
						  <label class="info">${data.submitted}</label><br>
						  <h3>resolved: </h3>
						  <label id="resolved" contenteditable class="info">${data.resolved}</label><br>
						  <h3>description: </h3>
						  <label class="info">${data.description}</label>
						  <h3>receipt: </h3>
						  <label class="info">${data.receipt}</label>
						  <h3>author_id: </h3>
						  <label class="info">${data.author_id}</label>
						  <h3>resolver_id: </h3>
						  <label id="resolver_id" contenteditable class="info">${data.resolver_id}</label>
						  <h3>status_id: </h3>
						  <label id="status_id" contenteditable class="info">${data.status_id}</label>
						  <h3>type_id: </h3>
						  <label class="info">${data.type_id}</label><hr>
						  <button type="submit" class="enter" onclick="updateStatus()">Update</button>`;
		container.append(info);
	
}

async function updateStatus() {
	
	let reimb_id = 	document.getElementById("reimb_id").textContent;
	let resdate = document.getElementById("resolved").textContent;
	let resolver = document.getElementById("resolver_id").textContent;
	let status = document.getElementById("status_id").textContent;
	
	let update = {
		
		reimb_id,
		resdate,
		resolver,
		status
	}
	try{
		let req = await fetch('http://localhost:8080/project1/api/updatestatus', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(update)
		});
		
		let res= await req.json();
	} catch(e){
		alert('Update failed!');
		return;
	}
}


async function getEmployees(){
	while(emp) {
	let res = await fetch('http://localhost:8080/project1/api/viewallemp');
	let data = await res.json();
	viewemp(data);
	emp = false;
	}
}
async function getAllpend(){
	while(pend) {
	let res = await fetch('http://localhost:8080/project1/api/viewallpend');
	let data = await res.json();
	viewreq(data, 'allpendreimb');
	pend = false;
	}
}

async function getAllres(){
	while(reso) {
	let res = await fetch('http://localhost:8080/project1/api/viewallres');
	let data = await res.json();
	viewreq(data, 'allresreimb');
	reso = false;
	}
}
async function getEmpreq(e){
	
	e.preventDefault();
	let username = document.getElementById("empreq").value;
	let empusername = {username}
	//console.log(reqid);
	try{
		let req = await fetch('http://localhost:8080/project1/api/viewempreq', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(empusername)
		});
		
		let res= await req.json();
		//console.log(res);
		viewreq(res, 'empreimb');
	} catch(e){
		alert('Username incorrect!');
		return;
	}
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

function viewemp(data) { 
	var container = document.getElementById('allemp');
	var table = document.createElement('table');
	var row = document.createElement('tr');
	
	row.innerHTML = `<th>user_id</th>
					 <th>username</th>
					 <th>first_name</th>
					 <th>last_name</th>
					 <th>email</th>
					 <th>role_id</th>`;	
	table.append(row);					
	container.append(table);
	
	for(reqObj of data) {
		row = document.createElement('tr');
	
		row.innerHTML = `<td>${reqObj.user_id}</td>
						 <td>${reqObj.username}</td>
						 <td>${reqObj.first_name}</td>
						 <td>${reqObj.last_name}</td>
						 <td>${reqObj.email}</td>
						 <td>${reqObj.role_id}</td>`;
		table.append(row);
		container.append(table);
	}

}

function logout() {
  window.localStorage.clear();
  window.location.reload(true);
  window.location.replace('../html/login.html');
}

