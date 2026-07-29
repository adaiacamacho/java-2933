const URL = 'api/v1/productos';

let numeroPaginas;

let pagina = 1;
let texto = '';

let alerta;
let pNumero, pInicio, pAnterior, pSiguiente, pFin;
let fila;
let carritoTbody, carritoSubtotal, carritoIva, carritoTotal;

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

    document.querySelector('#anadir-carrito').addEventListener('submit', anadirCarrito);

    document.querySelector('#ver-carrito').addEventListener('click', carrito);
    document.querySelector('#vaciar-carrito').addEventListener('click', vaciarCarrito);
}

function variablesGlobales() {
    alerta = document.querySelector('#alerta');

    pInicio = document.querySelector('#p-inicio a');
    pAnterior = document.querySelector('#p-anterior a');
    pNumero = document.querySelector('#p-numero a');
    pSiguiente = document.querySelector('#p-siguiente a');
    pFin = document.querySelector('#p-fin a');

    fila = document.querySelector('#listado .row');

    carritoTbody = document.querySelector('#carrito tbody');
    carritoSubtotal = document.querySelector('#carrito tfoot tr:first-of-type td:last-of-type');
    carritoIva = document.querySelector('#carrito tfoot tr:nth-of-type(2) td:last-of-type');
    carritoTotal = document.querySelector('#carrito tfoot tr:nth-of-type(3) td:last-of-type');
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

    const id = e.currentTarget.parentElement.id;

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

    document.querySelector('#cantidad').value = 1;

    mostrar('detalle');
}

function listado(e) {
    e && e.preventDefault();

    actualizarListadoProductos();

    mostrar('listado');
}

function carrito() {
    const lineas = obtenerCarrito();

    let subtotal = 0, iva = 0, total = 0;

    carritoTbody.innerHTML = '';

    for (const linea of lineas) {
        const lineaSubtotal = (linea.producto.precio * linea.cantidad) * (1.0 - 0.21);
        const lineaIva = linea.producto.precio * linea.cantidad * 0.21;
        const lineaTotal = linea.producto.precio * linea.cantidad;

        subtotal += lineaSubtotal;
        iva += lineaIva;
        total += lineaTotal;

        const tr = document.createElement('tr');
        tr.className = 'align-middle';

        tr.innerHTML = `
			<td><a href="carrito/borrar?id=${linea.producto.id}"><i
					class="text-danger bi bi-trash"></i></a></td>
			<td>${linea.producto.nombre}</td>
			<td class="text-end">${euro(linea.producto.precio)}</td>
			<td class="text-center">
				<div class="input-group" data-id="${linea.producto.id}">
					<button onclick="gestionarCantidadCarrito(this)" class="btn btn-outline-secondary menos"
						value="-1">
						<i class="bi ${linea.cantidad - 1 != 0 ? 'bi-dash' : 'bi-trash'}"></i>
					</button>
	
					<input id="cantidad" readonly type="text" pattern="\d+"
						class="form-control text-center" value="${linea.cantidad}"
						min="1">
	
					<button onclick="gestionarCantidadCarrito(this)" class="btn btn-outline-secondary mas"
						value="1">
						<i class="bi bi-plus-lg"></i>
					</button>
				</div>
			</td>
			<td class="text-end d-none d-md-table-cell">${euro(lineaSubtotal)}</td>
			<td class="text-end d-none d-md-table-cell">${euro(lineaIva)}</td>
			<td class="text-end fw-bold">${euro(lineaTotal)}</td>
			`;

        carritoTbody.appendChild(tr);
    }

    carritoSubtotal.textContent = euro(subtotal);
    carritoIva.textContent = euro(iva);
    carritoTotal.textContent = euro(total);

    mostrar('carrito');
}

function buscar(e) {
    e && e.preventDefault();

    pagina = 1;
    texto = document.querySelector('[name=texto]').value;

    listado();
}

async function anadirCarrito(e) {
    e && e.preventDefault();

    const id = Number(document.querySelector('[name=id]').value);
    const cantidad = Number(document.getElementById('cantidad').value);

    const respuesta = await fetch(`${URL}/${id}`);
    const producto = await respuesta.json();

    anadirProductoACarrito(producto, cantidad);

    carrito();
}

function mostrar(id) {
    const secciones = document.querySelectorAll('main>section');

    for (const seccion of secciones) {
        seccion.style.display = 'none';
    }

    document.getElementById(id).style.display = null;
}

function obtenerCarrito() {

    const carrito = localStorage.getItem('carrito');

    if (!carrito) {
        return guardarCarrito([]);
    }

    return JSON.parse(carrito);
}

function guardarCarrito(carrito) {
    localStorage.setItem('carrito', JSON.stringify(carrito));

    return carrito;
}

function anadirProductoACarrito(producto, cantidad) {
    let carrito;
    const carritoOriginal = obtenerCarrito();

    const lineaExistente = carritoOriginal.find(linea => linea.producto.id === producto.id);

    if (lineaExistente) {
        lineaExistente.cantidad += cantidad;

        if (lineaExistente.cantidad <= 0) {
            carrito = carritoOriginal.filter(linea => linea.producto.id !== producto.id);
        } else {
            carrito = carritoOriginal;
        }
    } else {
        carrito = [...carritoOriginal, { producto, cantidad: Number(cantidad) }];
    }

    guardarCarrito(carrito);
}

function vaciarCarrito() {
    localStorage.removeItem('carrito');

    listado();
}

window.gestionarCantidadCarrito = function(that) {
    const id = Number(that.parentElement.dataset.id);
    const esMenos = that.classList.contains('menos');

    anadirProductoACarrito({ id }, esMenos ? -1 : 1);

    carrito();
}















