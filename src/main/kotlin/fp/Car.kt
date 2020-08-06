package fp

import java.util.function.Supplier

data class Car(val name: String, val position: Int) {

    // not recommended
    fun move(isMovable: Supplier<Boolean>): Car {
        if (isMovable.get()) {
            return copy(position = position + 1)
        }
        return this
    }

    fun move(isMovable: () -> Boolean): Car {
        if (isMovable()) {
            return copy(position = position + 1)
        }
        return this
    }
}
