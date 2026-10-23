package main.org.example

class MaquinaCafe {

    enum class EstadoMaquina {
        IDLE,
        SELECCION_BEBIDA,
        BEBIDA_NO_DISPONIBLE,
        SELECCION_AZUCAR,
        PREPARACION_SIN_AZUCAR,
        SELECCION_CANTIDAD_AZUCAR,
        AZUCAR_NO_DISPONIBLE,
        PREPARACION_CON_AZUCAR,
        RECOGIDA_CAFE,
        ERROR
    }

    var estadoActual: EstadoMaquina = EstadoMaquina.IDLE
        private set

    var bebidaSeleccionada: Bebida? = null
        private set

    var quiereAzucar: Boolean = false
        private set

    var cantidadAzucarDeseada: Int = 0
        private set

    var stockAzucar: Int = 5

    fun iniciar() {
        when (estadoActual) {
            EstadoMaquina.IDLE -> estadoActual = EstadoMaquina.SELECCION_BEBIDA
            else -> {}
        }
    }

    fun seleccionarBebida(bebida: Bebida) {
        when (estadoActual) {
            EstadoMaquina.SELECCION_BEBIDA -> {
                bebidaSeleccionada = bebida
                if (bebida.cantidad > 0) {
                    bebida.cantidad--
                    estadoActual = EstadoMaquina.SELECCION_AZUCAR
                } else {
                    estadoActual = EstadoMaquina.BEBIDA_NO_DISPONIBLE
                }
            }
            else -> {}
        }
    }

    fun elegirAzucar(quiere: Boolean) {
        when (estadoActual) {
            EstadoMaquina.SELECCION_AZUCAR,
            EstadoMaquina.AZUCAR_NO_DISPONIBLE -> {
                quiereAzucar = quiere
                if (!quiere) {
                    estadoActual = EstadoMaquina.PREPARACION_SIN_AZUCAR
                } else {
                    if (stockAzucar > 0) {
                        estadoActual = EstadoMaquina.SELECCION_CANTIDAD_AZUCAR
                    } else {
                        estadoActual = EstadoMaquina.AZUCAR_NO_DISPONIBLE
                    }
                }
            }
            else -> {}
        }
    }

    fun seleccionarCantidadAzucar(cantidad: Int) {
        when (estadoActual) {
            EstadoMaquina.SELECCION_CANTIDAD_AZUCAR -> {
                cantidadAzucarDeseada = cantidad
                if (stockAzucar >= cantidad) {
                    stockAzucar -= cantidad
                    estadoActual = EstadoMaquina.PREPARACION_CON_AZUCAR
                } else {
                    estadoActual = EstadoMaquina.AZUCAR_NO_DISPONIBLE
                }
            }
            else -> {}
        }
    }

    fun terminarPreparacion() {
        when (estadoActual) {
            EstadoMaquina.PREPARACION_SIN_AZUCAR,
            EstadoMaquina.PREPARACION_CON_AZUCAR -> {
                estadoActual = EstadoMaquina.RECOGIDA_CAFE
            }
            else -> {}
        }
    }

    fun retirarCafe() {
        when (estadoActual) {
            EstadoMaquina.RECOGIDA_CAFE,
            EstadoMaquina.BEBIDA_NO_DISPONIBLE -> {
                reset()
            }
            else -> {}
        }
    }

    fun error() {
        estadoActual = EstadoMaquina.ERROR
    }

    fun reset() {
        bebidaSeleccionada = null
        quiereAzucar = false
        cantidadAzucarDeseada = 0
        estadoActual = EstadoMaquina.IDLE
    }
}