const URL = 'api/v1/videos/'

const ul = document.querySelector('ul');

const respuesta = await fetch(URL);
const videos = await respuesta.json();

for(const video of videos) {
	const li = document.createElement('li');
	
	li.innerHTML = `<a href="${video.url}">${video.titulo}</a>`;
	
	ul.appendChild(li);
}
