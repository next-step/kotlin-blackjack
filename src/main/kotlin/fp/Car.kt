package fp

data class Car(val name: String, val position: Int) {
    fun move(moveStrategy: () -> Boolean): Car {
        if (moveStrategy()) {
            return copy(position = position + 1)
        }
        return this
    }
}
