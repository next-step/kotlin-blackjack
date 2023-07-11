package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun score(): Int {
        return cards.score()
    }

    fun canReceive(): Boolean {
        return score() < Cards.BLACKJACK
    }
}
