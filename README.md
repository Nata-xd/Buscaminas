# Buscaminas en Terminal — Java

> Proyecto final Pensamiento Computacional  
---
## ¿Cómo se juega?

### 1. Menú principal

Al iniciar, el programa te mostrará las siguientes opciones:

```
\n--- BUSCAMINAS ---

1). Facil(9x9 con 10 bombas)
2). Medio(16x16 con 40 bombas)
3). Dificil(16x30 con 99 bombas)
4). Personalizado
```
Y deberás elegir alguna de ellas para iniciar la partida.

### 2. Durante la partida

El jugador ingresa la **fila** y la **columna** de la casilla que desea jugar, y luego elige entre:

- **Descubrir** la casilla
- **Colocar** una bandera 


## Símbolos en el tablero

| Símbolo | Significado                      |
|---------|----------------------------------|
| `?`     | Celda no descubierta             |
| ` `     | Celda vacía  |
| `!`     | Bandera colocada por el jugador  |
| `Ð`     | Bombas (visible al perder)       |

---

## Condiciones para el fin de partida

| Resultado | Condición |
|-----------|-----------|
| **Derrota** | El jugador descubre una casilla con bomba |
| **Victoria** | El jugador descubre todas las casillas sin bomba |
