package blackjack.domain

class Dealer(private val cards: Cards = Cards.create(ShufflingStrategy.RandomShuffling)) {
    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.forEach { gambler ->
            gambler.receive(cards.draw(), cards.draw())
        }
    }
}