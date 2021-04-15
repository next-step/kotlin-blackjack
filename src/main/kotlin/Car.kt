data class Car(val name: String, val position: Int) {
    fun move(moveAble: () -> Boolean): Car {
        if (moveAble()) {
            return copy(position = position + 1)
        }
        return this
    }
}
