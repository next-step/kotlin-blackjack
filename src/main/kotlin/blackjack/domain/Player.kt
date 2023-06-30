package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int = cards.score
}
