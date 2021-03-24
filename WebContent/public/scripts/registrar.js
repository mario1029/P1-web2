/**
 * 
 */

var registerButton = document.getElementById("register-button")
var registerForm = document.getElementById("register-form")


const SendRegister = (e) => {
	
    var form = new FormData(registerForm)
	alert("Enviando...")
	alert(form.get("username"))
	
	
	
	//http://localhost:8080/WEB2_P1
	//https://web2-parte2.herokuapp.com
	fetch('https://web2-parte3.herokuapp.com/Registro', {
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

		});
}

registerButton.onclick = SendRegister