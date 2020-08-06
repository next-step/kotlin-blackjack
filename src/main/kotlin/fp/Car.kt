package fp

data class Car(val name: String, val position: Int) {
    fun move(moveornot: () -> Boolean): Car {
        return if (moveornot()) {
            copy(position = position + 1)
        } else this
    }
}
