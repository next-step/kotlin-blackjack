package blackjack.domain

class Player(val name: String, rule: BlackJackRule) {

    val cards: PlayerCards = PlayerCards(rule)

    fun receive(card: PlayingCard) {
        cards.add(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
