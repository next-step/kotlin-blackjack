package study

data class Car(val name: String, val position: Int) {
    fun move(moveStrategy: MoveStrategy): Car {
        return if (moveStrategy.isMovable) {
            copy(position = position + 1)
        } else {
            this
        }
    }

    fun move2(isMovable: () -> Boolean) : Car {
        return if (isMovable()) {
            copy(position = position + 1 )
        } else {
            this
        }
    }
}