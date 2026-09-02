const URL = 'api/v1/videos/';

window.listado = async function() {
    const listadoVideos = document.querySelector('#listado-videos');

    const respuesta = await fetch(URL);
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
    const respuesta = await fetch(URL + id);
    const video = await respuesta.json();

    document.querySelector('#detalle iframe').src = video.url; // 'https://www.youtube.com/embed/fLexgOxsZu0';
    document.querySelector('#detalle iframe').title = video.titulo; //'Video de Bruno Mars';
    document.querySelector('#detalle .card-title').textContent = video.titulo; //'Video de Bruno Mars';
    document.querySelector('#detalle .card-text').textContent = video.descripcion; // 'Bla bla bla';
    document.querySelector('#detalle .card-footer small').textContent = video.fecha; //'FECHA';

    mostrar('detalle');
}

window.detalle(2);