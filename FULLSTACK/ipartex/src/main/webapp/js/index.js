const URL_MENSAJES = 'api/v3/mensajes';
const URL_USUARIOS = 'api/v3/usuarios';

const form = document.querySelector('form');

// Actualización automática de mensajes cada segundo
// setInterval(actualizarListadoMensajes, 1000);

actualizarListadoMensajes();
actualizarDesplegableUsuarios();

form.addEventListener('submit', async e => {
	e.preventDefault();
	
	const mensaje = {
		usuario: {
			id: form['id-usuario'].value
		},
		texto: form.texto.value
	};
	
	const respuesta = await fetch(URL_MENSAJES, {
		method: 'POST',
		body: JSON.stringify(mensaje),
		headers: {
			'Content-type': 'application/json'
		},
	});
	
	console.log(respuesta);
	
	actualizarListadoMensajes();
	
	form.texto.value = '';
	form.texto.focus();
});

async function actualizarListadoMensajes() {
    const respuesta = await fetch(URL_MENSAJES);
    const mensajes = await respuesta.json();

    const ul = document.querySelector('ul');

    ul.innerHTML = '';

    for (const mensaje of mensajes) {
        const li = document.createElement('li');

		li.className = 'card my-4';
		
        li.innerHTML = `
		  <div class="card-body">
		  	<h5>${mensaje.nombre}</h5>
		  	<p class="card-text">${mensaje.texto}</p>
		  </div>
		  <div class="card-footer">
		    <small class="text-body-secondary">${mensaje.fechaHora}</small>
		  </div>
		`;

        ul.appendChild(li);
    }
}

async function actualizarDesplegableUsuarios() {
    const respuesta = await fetch(URL_USUARIOS);
    const usuarios = await respuesta.json();

    const select = document.querySelector('select');

    for (const usuario of usuarios) {
        const option = document.createElement('option');

		option.value = usuario.id;
		
		option.innerText = usuario.nombre;

        select.appendChild(option);
    }
}
