package blackjack.domain

object Dealer {
    private val cards: Cards = Cards.create(ShufflingStrategy.RandomShuffling)

    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            gambler.receive(cards.draw(), cards.draw())
        }
    }

    fun dealCard(gambler: Gambler) {
        gambler.receive(cards.draw())
    }
}