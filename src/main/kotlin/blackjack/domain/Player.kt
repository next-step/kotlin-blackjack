package blackjack.domain

class Player(
    val name: String,
    hand: Hand = Hand(),
) : BaseBlackjackMember(hand = hand) {
    init {
        updateState()
    }

    fun draw(deck: Deck) {
        receiveCard(deck.pop())
        updateState()
    }

    fun endTurn() {
        state = State.STAY
    }

    override fun canDraw(): Boolean =
        getScore() <= BlackjackRule.TARGET_SCORE && state == State.HIT

    override fun updateState() {
        state = State.fromPlayer(hand)
    }
}
