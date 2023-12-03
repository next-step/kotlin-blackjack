package blackjack.domain

class Dealer(
    hand: Hand = Hand(),
) : BaseBlackjackMember(hand = hand) {
    init {
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

    override fun canDraw(): Boolean = state == State.HIT

    override fun updateState() {
        state = State.fromDealer(hand)
    }
}
