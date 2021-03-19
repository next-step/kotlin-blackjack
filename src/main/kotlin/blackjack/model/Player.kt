package blackjack.model

class Player(private val name: String) {
    override fun toString(): String {
        return "${this.javaClass.name}{$name}"
    }
}