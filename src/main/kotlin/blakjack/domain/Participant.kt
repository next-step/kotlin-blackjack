package blakjack.domain

sealed class Participant(
    val name: String,
    val type: ParticipantType,
) {
    var cards: Cards = Cards.empty()
        private set

    private var status: Status = Status.HIT

    val score: Int
        get() = cards.score()

    open fun win(other: Participant) {
        other.lose()
    }

    abstract fun draw()

    abstract fun lose()

    abstract fun profit(): Money

    open fun hit(card: Card) {
        this.cards = cards.add(card)
        hit()
        bustIfOver21()
    }

    fun add(cards: Cards) {
        this.cards = cards.add(cards)
        hit()
        blackjackIf21()
        bustIfOver21()
    }

    fun isBust(): Boolean {
        return this.status == Status.BUST
    }

    fun isNotBust(): Boolean {
        return !isBust()
    }

    fun isBlackjack(): Boolean {
        return this.status == Status.BLACKJACK
    }

    fun isHit(): Boolean {
        return this.status == Status.HIT
    }

    fun isStand(): Boolean {
        return this.status == Status.STAND
    }

    private fun bustIfOver21() {
        if (score > BLACKJACK_SCORE) {
            bust()
        }
    }

    private fun blackjackIf21() {
        if (score == BLACKJACK_SCORE) {
            blackjack()
        }
    }

    fun bust() {
        this.status = Status.BUST
    }

    fun blackjack() {
        this.status = Status.BLACKJACK
    }

    fun hit() {
        this.status = Status.HIT
    }

    fun stand() {
        this.status = Status.STAND
    }

    fun isWin(other: Participant): Boolean {
        return this.score > other.score
    }

    fun isDraw(other: Participant): Boolean {
        return this.score == other.score
    }

    enum class ParticipantType {
        DEALER, PLAYER
    }

    enum class Status {
        BUST, HIT, STAND, BLACKJACK
    }
}
