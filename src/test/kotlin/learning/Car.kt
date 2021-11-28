package learning

data class Car(val name: String, val position: Int) {
    fun moved(strategy: MoveStrategy): Car = when (strategy.isMovable()) {
        true -> copy(position = position + 1)
        false -> this
    }
}

fun interface MoveStrategy {
    fun isMovable(): Boolean
}
