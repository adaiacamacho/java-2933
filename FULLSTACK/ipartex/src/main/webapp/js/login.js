const URL_USUARIO_POR_EMAIL = 'api/v3/usuarios/buscar/por-email?email=';

const login = document.querySelector('.formLogin');


login.addEventListener('submit', async e =>{
	e.preventDefault();
	
	const usuario={
		email: login.email.value,
		password: login.contra.value
	};
	
	const query=URL_USUARIO_POR_EMAIL+usuario.email;
	
	const respuesta = await fetch(query, {
	        method: 'GET',
	        headers: {
	            'Content-type': 'application/json'
	        },
	    });
	
		console.log(respuesta);
	
	if(respuesta.ok){
		const datosUsuario=await respuesta.json();
		console.log(datosUsuario)
		/**aqui se llamaria al Rest con un post en login para validar en el back
		 * **/
		if(datosUsuario.password==usuario.password){
			window.location.href = "index.html";
		}else{
			alert("Credenciales incorrectas");
		login.contra.value='';
		login.contra.focus();
		}
	}else{
		alert("No se encontró un usuario con este email");
		login.email.value='';
		login.email.focus();
		login.contra.value='';
	}
	
});







