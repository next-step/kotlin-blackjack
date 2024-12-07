package blackjack.domain

sealed class Participant(
    open val name: String,
    open var status: PlayerStatus = PlayerStatus.PLAYING,
    protected open val hands: Hands,
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    val cards: String
        get() = hands.toString()

    val isPlayable: Boolean
        get() = status.isPlayable()

    open fun initialDraw(deck: Deck) {
        hands.add(deck.draw())
        hands.add(deck.draw())
        handleStatus()
    }

    fun hit(deck: Deck) {
        if (!isPlayable) {
            return
        }

        val card = deck.draw()
        hands.add(card)
        handleStatus()
    }

    abstract fun handleStatus()
}
