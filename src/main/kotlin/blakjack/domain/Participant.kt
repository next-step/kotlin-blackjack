package blakjack.domain

sealed class Participant(
    val name: String
) {
    var cards: Cards = Cards.empty()
        private set

    private var status: ParticipantStatus = ParticipantStatus.STAY

    val score: Int
        get() = cards.score()

    val isOver21: Boolean
        get() = cards.scoreWithAceAsOne() > BLACKJACK_SCORE

    fun add(card: Card) {
        this.cards = cards.add(card)
        bustIfOver21()
    }

    fun add(cards: Cards) {
        this.cards = cards.add(cards)
        bustIfOver21()
    }

    fun bust() {
        this.status = ParticipantStatus.BUST
    }

    fun isBust(): Boolean {
        return this.status == ParticipantStatus.BUST
    }

    private fun bustIfOver21() {
        if (isOver21) {
            bust()
        }
    }

    enum class ParticipantStatus {
        STAY, BUST
    }

    enum class ParticipantAction {
        HIT, STAND
    }
}
