package blackjack.domain

class DealingShoe(private val deck: Deck = Deck()) {
    fun dealTwoCardsEach(gamblers: Gamblers) {
        gamblers.receiveTwoCardsEach(deck)
    }

    fun dealCard(gambler: Gambler) {
        gambler.receive(deck.draw())
    }
}
