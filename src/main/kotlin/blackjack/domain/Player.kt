package blackjack.domain

class Player(
    val name: String,
    val hands: Hands = Hands(),
) {
    val score: Int
        get() = hands.calculateTotalValue()

    val handSize: Int
        get() = hands.size

    var status: PlayerStatus = PlayerStatus.PLAYING
        private set

    fun isPlayable(): Boolean = status.isPlayable()

    fun toStay() {
        this.status = PlayerStatus.STAY
    }

    fun hit(deck: Deck) {
        if (!isPlayable()) {
            return
        }

        val card = deck.draw()
        hands.add(card)
        handleBurst()
    }

    private fun handleBurst() {
        status = status.handleBurst(score)
    }
}
