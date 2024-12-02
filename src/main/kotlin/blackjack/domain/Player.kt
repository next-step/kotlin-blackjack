package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) {
    val hands: Int
        get() = cards.size

    val score: Int
        get() = cards.calculateTotalValue()

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
        cards.add(card)
        status = status.handleBurst(score)
    }
}
