package blackjack.domain

class Dealer(val cards: Cards = Cards()) {
    val cardSet get() = cards.cards

    fun drawBy(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }

    fun isBurst(): Boolean {
        return cards.score().burst()
    }

    fun isHit(): Boolean {
        return !cards.score().burst()
    }
}
