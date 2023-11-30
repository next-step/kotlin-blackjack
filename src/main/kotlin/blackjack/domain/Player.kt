package blackjack.domain

class Player(
    val name: String,
    private val hand: Hand = Hand(),
) {
    var state: State = State.HIT
        private set

    init {
        updateState()
    }

    fun getCardList(): List<Card> = hand.cards

    fun canDraw(): Boolean =
        getScore() <= BlackjackRule.TARGET_SCORE && state == State.HIT

    fun draw(deck: Deck) {
        receiveCard(deck.pop())
        updateState()
    }

    fun endTurn() {
        state = State.STAY
    }

    fun getScore(): Int = hand.getScore()

    private fun receiveCard(card: Card) {
        hand.add(card)
    }

    private fun updateState() {
        state = State.fromHand(hand)
    }
}
