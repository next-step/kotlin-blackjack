package step2.blackjack.model

data class Gambler(
    val name: Name,
    val cards: Cards
) {

    fun receive(card: Card) = cards + card

    companion object {
        fun from(name: Name): Gambler = Gambler(name, Cards.empty())
    }
}
