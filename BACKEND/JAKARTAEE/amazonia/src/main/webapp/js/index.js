const URL = 'api/v1/productos';

let numeroPaginas;

let pagina = 1;
let texto = '';

let alerta;
let pNumero, pInicio, pAnterior, pSiguiente, pFin;
let fila;

function euro(cantidad) {
    const fmt = new Intl.NumberFormat('es-ES', {
        style: 'currency',
        currency: 'EUR',
        minimumFractionDigits: 2,
        useGrouping: true,
    });

    return fmt.format(cantidad);
}

window.addEventListener('DOMContentLoaded', async () => {
    variablesGlobales();
	
	eventosGlobales();
	
    alerta.style.display = 'none';

	listado();
});

function eventosGlobales() {
    masMenosCantidad();

    document.querySelectorAll('.navbar-brand, .navbar-nav:first-of-type .nav-link:first-of-type')
        .forEach(enlaceListado => enlaceListado.addEventListener('click', listado));

    document.querySelector('#buscar-texto').addEventListener('submit', buscar);


    for (const enlacePaginacion of document.querySelectorAll('.pagination a')) {
        enlacePaginacion.addEventListener('click', paginacion);
    }
}

function variablesGlobales() {
    alerta = document.querySelector('#alerta');

    pInicio = document.querySelector('#p-inicio a');
    pAnterior = document.querySelector('#p-anterior a');
    pNumero = document.querySelector('#p-numero a');
    pSiguiente = document.querySelector('#p-siguiente a');
    pFin = document.querySelector('#p-fin a');
	
	fila = document.querySelector('#listado .row');
}

function masMenosCantidad() {
    const menos = document.querySelector('#menos');
    const mas = document.querySelector('#mas');
    const cantidad = document.querySelector('#cantidad');

    console.log(menos, mas, cantidad);

    menos.addEventListener('click', () => cantidad.value > 1 ? cantidad.value-- : cantidad.value);
    mas.addEventListener('click', () => cantidad.value++);
}

async function paginacion(e) {
    console.log(e);
    e.preventDefault();

    const id = e.target.parentElement.parentElement.id;

    console.log(id);

    switch (id) {
        case 'p-inicio': pagina = 1; break;
        case 'p-anterior': pagina > 1 && pagina--; break;
        case 'p-siguiente': pagina < numeroPaginas && pagina++; break;
        case 'p-fin': pagina = numeroPaginas; break;
    }

    console.log(pagina);

    actualizarListadoProductos();
}

async function actualizarListadoProductos() {
    const respuesta = await fetch(`${URL}?pagina=${pagina}&texto=${texto}`);
    const respuestaNumeroPaginas = await fetch(`${URL}/numero-paginas?texto=${texto}`);
    const productos = await respuesta.json();

    numeroPaginas = await respuestaNumeroPaginas.json();

    fila.innerHTML = '';

    for (const p of productos) {
        const div = document.createElement('div');
        div.className = 'col';
        div.innerHTML = `
			<div class="card h-100">
				<img src="fotos/${p.id}.jpg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">${p.nombre}</h5>
					<p class="card-text">${p.descripcion ?? ''}</p>
					<p class="card-text">
						<button class="btn btn-primary">Ver producto</button>
					</p>
				</div>
				<div class="card-footer">
					<small class="text-body-secondary">${euro(p.precio)}</small>
				</div>
			</div>
		`;

        div.querySelector(".btn").addEventListener('click', () => detalle(p.id));

        fila.append(div);
    }

    pNumero.textContent = `${pagina} de ${numeroPaginas}`;

    pInicio.classList.remove('disabled');
    pAnterior.classList.remove('disabled');
    pSiguiente.classList.remove('disabled');
    pFin.classList.remove('disabled');

    if (pagina === 1) {
        pInicio.classList.add('disabled');
        pAnterior.classList.add('disabled');
    }
	
	if (pagina === numeroPaginas) {
        pSiguiente.classList.add('disabled');
        pFin.classList.add('disabled');
    }
}

async function detalle(id) {

    console.log(id);

    const respuesta = await fetch(`${URL}/${id}`);
    const producto = await respuesta.json();

    document.querySelector('#detalle img').src = `fotos/${producto.id}.jpg`;
    document.querySelector('#detalle .card-title').textContent = `${producto.nombre}`;
    document.querySelector('#detalle .card-text:first-of-type').textContent = `${producto.descripcion ?? ''}`;
    document.querySelector('#detalle small').textContent = `${euro(producto.precio)}`;
    document.querySelector('#detalle input[type=hidden]').value = `${producto.id}`;

    mostrar('detalle');
}

function listado(e) {
    e && e.preventDefault();
    
	actualizarListadoProductos();
    
	mostrar('listado');
}

function buscar(e) {
    e && e.preventDefault();

	pagina = 1;
	texto = document.querySelector('[name=texto]').value;
	
    listado();
}

function mostrar(id) {
    const secciones = document.querySelectorAll('main>section');

    for (const seccion of secciones) {
        seccion.style.display = 'none';
    }

    document.getElementById(id).style.display = null;
}























