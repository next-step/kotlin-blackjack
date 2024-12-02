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
            reasonDone = PlayerReasonDone.BUSTED
        }
    }

    fun stand() {
        check(!isDone) { "이미 턴이 끝난 상태입니다." }
        reasonDone = PlayerReasonDone.STANDS
    }

    fun initialDrawFrom(deck: Deck) {
        drawFrom(deck)
        if (hand.isBlackjack()) {
            reasonDone = PlayerReasonDone.BLACKJACK
        }
    }

    private fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }
}
