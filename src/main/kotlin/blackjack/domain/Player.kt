package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val value: Int
        get() = hand.value()
    val isBlackjack: Boolean
        get() = hand.isBlackjack()
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

    fun outcome(dealer: Dealer): PlayerOutcome {
        if (isBusted) {
            return PlayerOutcome.LOSE
        }
        if (dealer.isBusted) {
            return PlayerOutcome.WIN
        }
        if (isBlackjack && !dealer.isBlackjack) {
            return PlayerOutcome.WIN
        }
        if (pushes(dealer)) {
            return PlayerOutcome.DRAW
        }
        if (beats(dealer)) {
            return PlayerOutcome.WIN
        }
        return PlayerOutcome.LOSE
    }

    private fun pushes(dealer: Dealer) = hand.pushes(dealer.hand)

    private fun beats(dealer: Dealer) = hand.beats(dealer.hand)

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
