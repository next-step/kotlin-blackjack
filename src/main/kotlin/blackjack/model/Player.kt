package blackjack.model

class Player(private val name: String) {
    private val cards = Cards()

    override fun toString(): String {
        return "${this.javaClass.name}{$name}"
    }
}
