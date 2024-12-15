package blackjack.domain

class Dealer(private val deck: Deck = Deck()) {
    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.receiveTwoCardsEach(deck)
    }

    fun dealCard(gambler: Gambler) {
        gambler.receive(deck.draw())
    }
}
