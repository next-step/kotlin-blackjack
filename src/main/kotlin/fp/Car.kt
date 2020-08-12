package fp

data class Car(val name: String, val position: Int) {
    fun move(isMovable: () -> Boolean): Car {
        if (isMovable()) {
            return copy(position = position + 1)
        }
        return this
    }
}
