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

    fun drawUntilOverMinimum(deck: Deck): Int {
        var countOfDraw = 0
        while (canDraw()) {
            receiveCard(deck.pop())
            updateState()
            countOfDraw++
        }

        return countOfDraw
    }

    private fun receiveCard(card: Card) {
        hand.add(card)
    }

    private fun updateState() {
        state = State.fromDealer(hand)
    }
}
