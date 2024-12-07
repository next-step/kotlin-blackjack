package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val value: Int
        get() = hand.value()
    val isBusted: Boolean
        get() = hand.isBusted()
    var reasonDone: PlayerReasonDone? = null
        private set
    val isDone: Boolean
        get() = reasonDone != null

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

    fun dealerDealtBlackjack() {
        done(PlayerReasonDone.DEALER_DEALT_BLACKJACK)
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
