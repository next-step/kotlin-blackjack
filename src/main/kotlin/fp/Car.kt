package fp

data class Car(val name: String, val position: Int) {
    fun move(checkCondition: () -> Boolean): Car {
        if (checkCondition()) {
            return copy(position = position + 1)
        }
        return this
    }
}
