package domain.player

import domain.Cards

class Player(val name: Name, val cards: Cards) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Player(name=$name, cards=$cards)"
    }
}
