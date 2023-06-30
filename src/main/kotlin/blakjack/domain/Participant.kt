package blakjack.domain

sealed class Participant(
    val name: String,
    val type: ParticipantType,
) {
    var cards: Cards = Cards.empty()
        private set

    private var status: ParticipantStatus = ParticipantStatus.STAY

    val score: Int
        get() = cards.score()

    val isOver21: Boolean
        get() = cards.scoreWithAceAsOne() > BLACKJACK_SCORE

    open fun win(other: Participant) {
        other.lose()
    }

    protected abstract fun lose()

    fun add(card: Card) {
        this.cards = cards.add(card)
        bustIfOver21()
    }

    fun add(cards: Cards) {
        this.cards = cards.add(cards)
        bustIfOver21()
    }

    fun isBust(): Boolean {
        return this.status == ParticipantStatus.BUST
    }

    private fun bustIfOver21() {
        if (isOver21) {
            bust()
        }
    }

    private fun bust() {
        this.status = ParticipantStatus.BUST
    }

    fun isWin(other: Participant): Boolean {
        return this.score > other.score
    }

    enum class ParticipantType {
        DEALER, PLAYER
    }

    enum class ParticipantStatus {
        STAY, BUST
    }

    enum class ParticipantAction {
        HIT, STAND
    }
}
