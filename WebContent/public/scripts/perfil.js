window.onload = function inicio(){
	var usuario = document.getElementById("username");
	var nombre = document.getElementById("nombre");
	var apellido = document.getElementById("apellido");
	var correo = document.getElementById("correo");
	var edad = document.getElementById("edad");
	var pais = document.getElementById("pais");
	var estado = document.getElementById("estado");
	var ciudad = document.getElementById("ciudad");
	var descripcion = document.getElementById("descripcion");
	var telf = document.getElementById("telf");
	
	


	//http://localhost:8080/WEB2_P1
	//https://web2-parte2.herokuapp.com
	fetch('http://localhost:8080/WEB2_P1/Perfil', {
	  	method: "POST",
		mode: "no-cors",
		headers: new Headers({
	  		'Content-Type': 'application/json'}),
		}).then(res => res.json())
		.catch(error => console.error('Error:', error))
		.then(response => {
			console.log('Resultado:', response)
			alert(response.message)
			usuario.value = response.usuario;
			apellido.value = response.apellido;
			nombre.value = response.nombre;
			correo.value = response.correo;
			pais.value = response.pais;
			edad.value = response.edad;
			telf.value = response.telf;
			descripcion.value = response.descripcion;
			ciudad.value = response.ciudad;
			estado.value = response.estado;
		});	

}
function Exit() {
	fetch('http://localhost:8080/WEB2_P1/Logout', {
	  	method: "POST",
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


var exitbutton = document.getElementById("exit-button")
exitbutton.onclick = Exit


function readFile(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
 
            reader.onload = function (e) {
                var filePreview = document.createElement('img');
                filePreview.id = 'file-preview';
                //e.target.result contents the base64 data from the image uploaded
                filePreview.src = e.target.result;
 				filePreview.height = 400;
 				filePreview.width = 500;
                var previewZone = document.getElementById('imagen');
                previewZone.appendChild(filePreview);
            }
 			console.log(input.files[0]);
 			console.log(input.files[0].name);
            reader.readAsDataURL(input.files[0]);

            var formData = new FormData();
            formData.append("archivo1", input.files[0]);
            formData.append("nombre", input.files[0].name)
            fetch('https://web2-parte3.herokuapp.com/Multimedia', {
            	method: "POST",
            	body: formData,
            	mode: "no-cors"})
        }
    }
 
    var fileUpload = document.getElementById('imagen-perfil');
    fileUpload.onchange = function (e) {
        readFile(e.srcElement);
    }
