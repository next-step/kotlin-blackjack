package blackjack.domain

abstract class BaseBlackjackMember(
    val hand: Hand,
) {
    var state: State = State.HIT
        protected set

    abstract fun canDraw(): Boolean

    fun getCardList(): List<Card> = hand.cards

    fun getScore(): Int = hand.getScore()

    protected fun receiveCard(card: Card) {
        hand.add(card)
    }

    protected abstract fun updateState()
}
