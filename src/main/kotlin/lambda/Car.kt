package lambda

data class Car(val name: String, val position: Int) {
    fun move(f: () -> Boolean): Car {
        if (f()) {
            return copy(position = position + 1)
        }
        return this
    }
}
