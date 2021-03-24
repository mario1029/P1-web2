var loginButton = document.getElementById("login-button")
var loginForm = document.getElementById("login-form")

const SendLoging = (e) => {
	
    var form = new FormData(loginForm)
	alert("Enviando...")
	alert(form.get("username"))
	
	
	//https://web2-parte2.herokuapp.com/Login
	//http://localhost:8080/WEB2_P1
	fetch('https://web2-parte3.herokuapp.com/Login', {
	  	method: "POST",
	  	body: form, 
	  	mode: "no-cors",
		headers: new Headers({
	  		'Content-Type': 'application/json'}),
		}).then(res => res.json())
		.catch(error => console.error('Error:', error))
		.then(response => {
			console.log('Resultado:', response)
			alert(response.message)
			window.location.href = response.redirect; 	
		});
}

loginButton.onclick = SendLoging