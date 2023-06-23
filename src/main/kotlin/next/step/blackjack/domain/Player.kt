package next.step.blackjack.domain

data class Player(val name: String, val cards: PlayerCards) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun isBurst(): Boolean = cards.isBurst()

    fun isBlackJack(): Boolean = cards.isBlackJack()

    companion object {

        fun of(name: String, cards: PlayerCards): Player = Player(name, cards)
    }
}