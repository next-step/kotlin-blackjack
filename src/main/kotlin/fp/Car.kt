package fp

data class Car(val name: String, val position: Int) {
    fun move(num: Int, isMove: () -> Boolean): Car {
        if (isMove()) {
            return copy(position = position + 1)
        }
        return this
    }
}
