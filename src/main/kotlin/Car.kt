data class Car(val name: String, val position: Int) {
    fun move(movable: () -> Boolean): Car {
        if (movable()) {
            return copy(position = position + 1)
        }
        return this
    }
}
