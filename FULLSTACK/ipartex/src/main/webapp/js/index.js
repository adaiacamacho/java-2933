const URL_MENSAJES = 'api/v3/mensajes';
const URL_USUARIOS = 'api/v3/usuarios';

const form = document.querySelector('form');

setInterval(actualizarListadoMensajes, 1000);

listarUsuarios();

form.addEventListener('submit', async e => {
    e.preventDefault();

    console.log(form.usuario.value);

    const buscarUsuario = await fetch(URL_USUARIOS + "/" + form.usuario.value, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        },
    });

    if (buscarUsuario.ok) {

        const usuario = await buscarUsuario.json();


        const mensaje = {
            usuario: usuario,
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
    } else {
        alert("Usuario no encontrado");
    }

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



async function listarUsuarios() {

    const respuesta = await fetch(URL_USUARIOS);
    const usuarios = await respuesta.json();

    const select = document.querySelector('select');

    select.innerHTML = '';

    for (const usuario of usuarios) {
        const op = document.createElement('option');

        op.value = usuario.id

        op.innerHTML = `${usuario.nombre}`;

        select.appendChild(op);
    }
}




