const URL_MENSAJES = 'api/v3/mensajes';

const form = document.querySelector('form');

setInterval(actualizarListadoMensajes, 1000);

form.addEventListener('submit', async e => {
	e.preventDefault();
	
	const mensaje = {
		nombre: form.nombre.value,
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
