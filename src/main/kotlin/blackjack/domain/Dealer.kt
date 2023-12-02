package blackjack.domain

class Dealer(
    override val hand: Hand = Hand(),
) : BlackjackMember {
    override var state: State = State.HIT
        private set

    init {
        updateState()
    }

    override fun canDraw(): Boolean = state == State.HIT

    override fun draw(deck: Deck) {
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
