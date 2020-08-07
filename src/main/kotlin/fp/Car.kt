package fp

data class Car(val name: String, val position: Int) {
    fun move(moveAction: () -> Boolean): Car {
        if (moveAction()) {
            return copy(position = position + 1)
        }
        return this
    }
}
