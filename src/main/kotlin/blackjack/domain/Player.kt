package blackjack.domain

data class Player(val name: String, val cards: List<Card>) {
    fun addCards(cards: List<Card>) = copy(cards = this.cards + cards)

    companion object {
        fun init(name: String): Player {
            return Player(name, emptyList())
        }
    }
}
