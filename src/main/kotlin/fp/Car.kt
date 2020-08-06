package fp

data class Car(val name: String, val position: Int) {
    fun move(moveOrStop: () -> Boolean): Car {
        if (moveOrStop()) {
            return copy(position = position + 1)
        }
        return this
    }
}
