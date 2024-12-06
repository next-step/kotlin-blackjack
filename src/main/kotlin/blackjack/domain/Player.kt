package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    var reasonDone: PlayerReasonDone? = null
        private set
    val value: Int
        get() = hand.value()
    val isDone: Boolean
        get() = reasonDone != null
    val isBusted: Boolean
        get() = hand.isBusted()

    init {
        require(name.isNotBlank()) { "이름이 빈 문자열입니다." }
    }

    fun initialDrawFrom(deck: Deck) {
        drawFrom(deck)
        if (hand.isBlackjack()) {
            done(PlayerReasonDone.PLAYER_HAS_BLACKJACK)
        }
    }

    fun hit(deck: Deck) {
        checkIsNotDone()
        hand.drawFrom(deck)
        if (isBusted) {
            done(PlayerReasonDone.PLAYER_BUSTED)
        }
    }

    fun stand() {
        checkIsNotDone()
        done(PlayerReasonDone.PLAYER_STANDS)
    }

    private fun checkIsNotDone() {
        check(!isDone) { "이미 턴이 끝난 상태입니다." }
    }

    private fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }

    private fun done(reasonDone: PlayerReasonDone) {
        this.reasonDone = reasonDone
    }
}
