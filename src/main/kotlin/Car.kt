data class Car(val name: String, val position: Int) {
    fun move(moveStrategy: MoveStrategy): Car {
        if (moveStrategy.isMovable) {
            return copy(position = position + 1)
        }
        return this
    }
}
