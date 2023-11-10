package blackjack.domain

class Player(val name: String, val cards: Cards = Cards()) {
    val cardSet get() = cards.cards

    fun hit(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }

    fun burst(): Boolean {
        return cards.score().isBurst()
    }
}
