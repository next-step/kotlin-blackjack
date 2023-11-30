package blackjack.domain

class Dealer(
    private val hand: Hand = Hand(),
) {
    var state: State = State.HIT
        private set

    init {
        updateState()
    }

    fun getCardList(): List<Card> = hand.cards

    fun canDraw(): Boolean = state == State.HIT

    fun draw(deck: Deck) {
        receiveCard(deck.pop())
        updateState()
    }

    private fun receiveCard(card: Card) {
        hand.add(card)
    }

    private fun updateState() {
        state = State.fromDealer(hand)
    }
}
