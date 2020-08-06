package functional

data class Car(val name: String, val position: Int) {
    fun move(canMove: () -> Boolean): Car {
        return if (canMove()) copy(position = position + 1)
        else this
    }
}
