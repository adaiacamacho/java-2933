const URL_VIDEOS = 'api/v1/videos/';
const URL_COMENTARIOS = 'api/v1/comentarios/';

let idVideo;

const comentarioForm = document.querySelector('#detalle form');

comentarioForm.addEventListener('submit', async (e) => {
	e.preventDefault();
	
	const comentario = { 
		idUsuario: 2, 
		texto: comentarioForm.texto.value,
		idVideo: idVideo 
	};
	
	console.log(comentario);
	
	const respuesta = await fetch(URL_COMENTARIOS, {
		method: 'POST',
		body: JSON.stringify(comentario),
		headers: { 'Content-type': 'application/json' },
	});
	
	const comentarioRecibido = await respuesta.json();
	
	console.log(comentarioRecibido);
	
	await rellenarComentariosVideo(idVideo);
});

window.listado = async function() {
    const listadoVideos = document.querySelector('#listado-videos');

    const respuesta = await fetch(URL_VIDEOS);
    const videos = await respuesta.json();

    listadoVideos.innerHTML = '';

    for (const video of videos) {
        const card = document.createElement('div');
        card.className = 'col';
        card.innerHTML = `	
		<div class="card h-100">
			<div class="ratio ratio-16x9 card-img-top">
			  	<iframe src="${video.url}" title="${video.titulo}" allowfullscreen></iframe>
			</div>
			<div class="card-body">
				<h5 class="card-title"><a href="javascript:detalle(${video.id})">${video.titulo}</a></h5>
				<p class="card-text">${video.descripcion}</p>
			</div>
			<div class="card-footer">
				<small class="text-body-secondary">${video.fecha}</small>
			</div>
		</div>
	`;

        listadoVideos.appendChild(card);
    }

    mostrar('listado');
}

function mostrar(id) {
    const sections = document.querySelectorAll('main>section');

    for (const section of sections) {
        section.style.display = 'none';
    }

    document.querySelector('#' + id).style.display = null;
}

window.detalle = async function(id) {
	idVideo = id;
	
    await rellenarDatosVideo(id); //'FECHA';

    await rellenarComentariosVideo(id);

    mostrar('detalle');
}

window.detalle(2);

async function rellenarComentariosVideo(id) {
    const respuestaComentarios = await fetch(`${URL_VIDEOS}${id}/comentarios`);
    const comentarios = await respuestaComentarios.json();

    const ul = document.querySelector('#detalle ul');

    ul.innerHTML = '';

    for (const comentario of comentarios) {
        const li = document.createElement('li');

        li.className = 'list-group-item d-flex justify-content-between align-items-start py-4';

        li.innerHTML = `
			<div class="ms-2 w-100">
				<div class="d-flex">
					<div class="fw-bold me-auto">
						${comentario.usuario}
					</div>
					<span class="badge text-bg-primary rounded-pill align-self-baseline">${comentario.fechaHora}</span>
				</div>
				<p>${comentario.texto}</p>
			</div>
		`;

        ul.appendChild(li);
    }
}

async function rellenarDatosVideo(id) {
    const respuesta = await fetch(URL_VIDEOS + id);
    const video = await respuesta.json();

    document.querySelector('#detalle iframe').src = video.url; // 'https://www.youtube.com/embed/fLexgOxsZu0';
    document.querySelector('#detalle iframe').title = video.titulo; //'Video de Bruno Mars';
    document.querySelector('#detalle .card-title').textContent = video.titulo; //'Video de Bruno Mars';
    document.querySelector('#detalle .card-text').textContent = video.descripcion; // 'Bla bla bla';
    document.querySelector('#detalle .card-footer small').textContent = video.fecha;
}
