package blackjack.model

class Player(val name: String) {
    val cards = Cards()

    override fun toString(): String {
        return name
    }
}
