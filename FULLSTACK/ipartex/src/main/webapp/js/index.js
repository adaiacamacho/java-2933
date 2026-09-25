const URL_MENSAJES = 'api/v3/mensajes';
const URL_USUARIOS = 'api/v3/usuarios';

const formMensajes = document.querySelector('#form-mensajes');
const formLogin = document.querySelector('#form-login');
const btnCerrarSesion = document.querySelector('#cerrar-sesion'); 

let usuario;

// Actualización automática de mensajes cada segundo
// setInterval(actualizarListadoMensajes, 1000);

formMensajes.classList.add('d-none');

actualizarListadoMensajes();

btnCerrarSesion.addEventListener('click', () => {
	usuario = undefined;
	
	formLogin.classList.remove('d-none');
	formMensajes.classList.add('d-none');
});

formLogin.addEventListener('submit', async e => {
    e.preventDefault();

    const usuarioLogin = {
        email: formLogin.email.value,
        password: formLogin.password.value,
    };

    const respuesta = await fetch(`${URL_USUARIOS}/autenticar?email=${usuarioLogin.email}&password=${usuarioLogin.password}`);

    if (respuesta.ok) {
        usuario = await respuesta.json();

        formLogin.classList.add('d-none');
        formMensajes.classList.remove('d-none');
    } else {
        alert('Login incorrecto');
    }
});

formMensajes.addEventListener('submit', async e => {
    e.preventDefault();

    const mensaje = {
        usuario: {
            id: usuario.id
        },
        texto: formMensajes.texto.value
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

    formMensajes.texto.value = '';
    formMensajes.texto.focus();
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
