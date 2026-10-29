let token;

const URL_LOGIN = 'http://localhost:8080/api/v2/usuarios/login';
const URL_PRUEBA = 'http://localhost:8080/api/v2/usuarios/prueba';

const form = document.querySelector('form');
const button = document.querySelector('body>button');

button.addEventListener('click', async () => {
    const respuesta = await fetch(URL_PRUEBA, {
        headers: { 'Authorization': 'Bearer ' + token }
    });

    if (!respuesta.ok) {
        alert('No se ha podido recibir la prueba');
    } else {
        const resultado = await respuesta.json();
        alert(resultado);
    }
});

form.addEventListener('submit', async e => {
    e.preventDefault();

    const usuario = {
        email: form.email.value,
        password: form.password.value
    };

    const respuesta = await fetch(URL_LOGIN, {
        method: 'POST',
        body: JSON.stringify(usuario),
        headers: {
            'Content-type': 'application/json'
        }
    });

    console.log(respuesta);

    if (!respuesta.ok) {
        alert('Login incorrecto');
        return;
    }

    const paquete = await respuesta.json();

    console.log(paquete);

    token = paquete.accessToken;
});