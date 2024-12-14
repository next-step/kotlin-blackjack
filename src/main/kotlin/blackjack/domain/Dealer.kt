package blackjack.domain

class Dealer(private val cards: Cards = Cards.create(ShufflingStrategy.RandomShuffling)) {
    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.receive(cards)
    }

    fun dealCard(gambler: Gambler) {
        gambler.receive(cards.draw())
    }
}