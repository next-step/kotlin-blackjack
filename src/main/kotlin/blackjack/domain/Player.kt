package blackjack.domain

class Player(
    val name: String,
    private val cards: Cards = Cards.emptyCards(),
    status: Status = Status.PLAYING,
) {
    val hands: Int
        get() = cards.size

    val score: Int
        get() = cards.calculateTotalValue()

    var status: Status = status
        private set

    enum class Status {
        PLAYING, STAY, BURST,
    }

    fun toStay() {
        this.status = Status.STAY
    }

    private fun toBurst() {
        this.status = Status.BURST
    }

    fun hit(deck: Deck) {
        checkBurst()

        val card = deck.draw()
        cards.add(card)
        handleBurst()
    }

    private fun checkBurst() {
        if (status == Status.BURST) {
            throw IllegalStateException("버스트 상태에서는 카드를 더 뽑을 수 없습니다.")
        }
    }

    private fun handleBurst() {
        if (score > 21) {
            toBurst()
        }
    }
}
