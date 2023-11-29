package blackjack.domain

class Player(
    val name: String,
    private val hand: Hand = Hand(),
    var state: State = State.HIT,
) {
    init {
        updateState()
    }

    fun getCardList(): List<Card> = hand.cards

    fun canDraw(): Boolean =
        getScore() <= BlackjackRule.TARGET_SCORE && state == State.HIT

    fun draw(deck: Deck) {
        hand.add(card = deck.pop())
        updateState()
    }

    fun endTurn() {
        state = State.STAY
    }

    fun getScore(): Int = hand.getScore()

    private fun updateState() {
        if (hand.getScore() > BlackjackRule.TARGET_SCORE) {
            state = State.BUST
        }

        if (
            hand.getScore() == BlackjackRule.TARGET_SCORE &&
            getCardList().count() == BlackjackRule.INITIAL_CARD
        ) {
            state = State.BLACKJACK
        }
    }
}
