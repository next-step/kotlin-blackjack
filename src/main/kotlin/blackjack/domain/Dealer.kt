package blackjack.domain

class Dealer private constructor(cards: Cards) {
    private val _cards: Cards = cards.deepCopy()
    val cards: Cards
        get() = _cards.deepCopy()

    fun giveTwoCardsTo(player: Player) =
        player.run {
            val twoCards = _cards.run { List(TWO) { remove() } }.toMutableList()
            takeFirstTwoCard(Cards(twoCards))
        }

    fun giveOneMoreCardTo(player: Player) = player.run { takeOneMoreCard(_cards.remove()) }

    companion object {
        private const val TWO = 2
        fun makeUpGame(): Dealer {
            val cards = Suit.values()
                .flatMap { suit ->
                    Denomination.values()
                        .map { Card(suit, it) }
                }
                .shuffled()
                .toMutableList()

            return Dealer(Cards(cards))
        }
    }
}
