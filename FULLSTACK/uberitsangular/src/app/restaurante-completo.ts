export interface RestauranteCompleto {
  id?: number;
  nombre: string;
  tiposComida: string[];

  platos: RestaurantePlato[];
}

export interface RestaurantePlato {
  id: number;
  nombre: string;
  precio: number;
  descripcion?: string;
}
