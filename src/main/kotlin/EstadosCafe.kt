class MaquinaCafe {

    enum class EstadoMaquina {
        IDLE,
        MONEDA_ACTIVADO,
        SELECCION_CAFE,
        SELECCION_AZUCAR,
        PRODUCIENDO,
        ESPERANDO_LISTO,
        RECOGIDA_CAFE,
        ERROR
    }

    var estadoActual: EstadoMaquina = EstadoMaquina.IDLE
        private set

    fun insertarMoneda() {
        when (estadoActual) {
            EstadoMaquina.IDLE -> estadoActual = EstadoMaquina.MONEDA_ACTIVADO
            else -> {}
        }
    }

    fun pulsarBoton() {
        when (estadoActual) {
            EstadoMaquina.MONEDA_ACTIVADO -> estadoActual = EstadoMaquina.SELECCION_CAFE
            else -> {}
        }
    }

    fun seleccionarCafe() {
        when (estadoActual) {
            EstadoMaquina.SELECCION_CAFE -> estadoActual = EstadoMaquina.SELECCION_AZUCAR
            else -> {}
        }
    }

    fun elegirAzucar(conAzucar: Boolean) {
        when (estadoActual) {
            EstadoMaquina.SELECCION_AZUCAR -> estadoActual = EstadoMaquina.PRODUCIENDO
            else -> {}
        }
    }

    fun terminarPreparacion() {
        when (estadoActual) {
            EstadoMaquina.PRODUCIENDO -> estadoActual = EstadoMaquina.ESPERANDO_LISTO
            else -> {}
        }
    }

    fun mostrarListo() {
        when (estadoActual) {
            EstadoMaquina.ESPERANDO_LISTO -> estadoActual = EstadoMaquina.RECOGIDA_CAFE
            else -> {}
        }
    }

    fun retirarCafe() {
        when (estadoActual) {
            EstadoMaquina.RECOGIDA_CAFE -> estadoActual = EstadoMaquina.IDLE
            else -> {}
        }
    }

    fun timeout() {
        error()
    }

    fun error() {
        when (estadoActual) {
            EstadoMaquina.MONEDA_ACTIVADO,
            EstadoMaquina.SELECCION_CAFE,
            EstadoMaquina.SELECCION_AZUCAR,
            EstadoMaquina.PRODUCIENDO,
            EstadoMaquina.RECOGIDA_CAFE,
            EstadoMaquina.ESPERANDO_LISTO -> {
                estadoActual = EstadoMaquina.ERROR
            }
            else -> {}
        }
    }

    fun reset() {
        when (estadoActual) {
            EstadoMaquina.ERROR -> estadoActual = EstadoMaquina.IDLE
            else -> {}
        }
    }
}