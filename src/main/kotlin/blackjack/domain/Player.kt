package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
    status: Status = Status.PLAYING,
) {
    val hands: Int
        get() = cards.size

    val score: Int
        get() = cards.calculateTotalValue()

    var status: Status = status
        private set

    enum class Status {
        PLAYING,
        STAY,
        BURST,
    }

    fun toStay() {
        this.status = Status.STAY
    }

    private fun toBurst() {
        this.status = Status.BURST
    }

    fun hit(deck: Deck) {
        if (!isPlayable()) {
            return
        }

        val card = deck.draw()
        cards.add(card)
        handleBurst()
    }

    fun isPlayable(): Boolean {
        return status == Status.PLAYING
    }

    private fun handleBurst() {
        if (score > 21) {
            toBurst()
        }
    }
}
