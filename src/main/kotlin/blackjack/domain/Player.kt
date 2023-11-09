package blackjack.domain

class Player(val name: String) {

    val cards: Cards = Cards()
    val cardSet get() = cards.cards

    fun init(trumpCard: TrumpCard) {
        repeat(2) {
            cards.add(trumpCard.draw())
        }
    }

    fun hit(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }

    fun burst(): Boolean {
        return cards.score().isBurst()
    }
}
