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

    init {
        require(name.isNotBlank()) { "이름이 빈 문자열입니다." }
    }

    fun hit(deck: Deck) {
        hand.drawFrom(deck)
        if (hand.isBusted()) {
            done(PlayerReasonDone.BUSTED)
        }
    }

    fun stand() {
        check(!isDone) { "이미 턴이 끝난 상태입니다." }
        done(PlayerReasonDone.STANDS)
    }

    fun initialDrawFrom(deck: Deck) {
        drawFrom(deck)
        if (hand.isBlackjack()) {
            done(PlayerReasonDone.BLACKJACK)
        }
    }

    private fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }

    private fun done(reasonDone: PlayerReasonDone) {
        this.reasonDone = reasonDone
    }
}
