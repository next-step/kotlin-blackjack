package blackjack.domain

sealed class Participant(
    open val name: String,
    open val hands: Hands,
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    val isPlayable: Boolean
        get() = status.isPlayable()

    var status: PlayerStatus = PlayerStatus.PLAYING
        protected set

    fun hit(deck: Deck) {
        if (!isPlayable) {
            return
        }

        val card = deck.draw()
        hands.add(card)
        handleStatus()
    }

    protected open fun handleStatus() {
        status = status.handleStatus(score)
    }
}
