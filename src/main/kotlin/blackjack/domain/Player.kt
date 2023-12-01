package blackjack.domain

class Player(
    val name: String,
    override val hand: Hand = Hand(),
) : BlackjackMember {
    override var state: State = State.HIT
        private set

    init {
        updateState()
    }

    override fun canDraw(): Boolean =
        getScore() <= BlackjackRule.TARGET_SCORE && state == State.HIT

    override fun draw(deck: Deck) {
        receiveCard(deck.pop())
        updateState()
    }

    fun endTurn() {
        state = State.STAY
    }

    private fun receiveCard(card: Card) {
        hand.add(card)
    }

    private fun updateState() {
        state = State.fromPlayer(hand)
    }
}
