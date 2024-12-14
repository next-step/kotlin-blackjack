package blackjack.domain

class Dealer(private val deck: Deck = Deck.create(ShufflingStrategy.RandomShuffling)) {
    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.receive(deck)
    }

    fun dealCard(gambler: Gambler) {
        gambler.receive(deck.draw())
    }
}