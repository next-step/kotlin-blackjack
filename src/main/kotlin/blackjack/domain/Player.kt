package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val value: Int get() = hand.value()
    val isBlackjack: Boolean get() = hand.isBlackjack()
    val isBusted: Boolean get() = hand.isBusted()
    var reasonDone: PlayerReasonDone? = null
        private set
    val isDone: Boolean get() = reasonDone != null
    var bet: Bet? = null
        private set

    init {
        require(name.isNotBlank()) { "이름이 빈 문자열입니다." }
    }

    fun initialDrawFrom(deck: Deck) {
        drawFrom(deck)
        if (isBlackjack) {
            done(PlayerReasonDone.PLAYER_HAS_BLACKJACK)
        }
    }

    fun hit(deck: Deck) {
        checkIsNotDone()
        hand.drawFrom(deck)
        // 패가 동시에 버스트와 21점일 수 없음
        when {
            hand.isTwentyOne -> stand()
            isBusted -> done(PlayerReasonDone.PLAYER_BUSTED)
        }
    }

    fun stand() {
        checkIsNotDone()
        done(PlayerReasonDone.PLAYER_STANDS)
    }

    fun dealerDealtBlackjack() {
        done(PlayerReasonDone.DEALER_DEALT_BLACKJACK)
    }

    fun pushes(dealer: Dealer) = hand.pushes(dealer.hand)

    fun beats(dealer: Dealer) = hand.beats(dealer.hand)

    fun placeBet(bet: Bet) {
        this.bet = bet
    }

    fun result(dealer: Dealer): PlayerResult {
        checkIsDone()
        checkBetIsNotNull()
        return PlayerResult(name, requireNotNull(bet), outcome(dealer))
    }

    private fun checkIsDone() {
        check(isDone) { "턴이 끝나지 않았습니다." }
    }

    private fun checkBetIsNotNull() {
        check(bet != null) { "베팅이 없습니다." }
    }

    private fun checkIsNotDone() {
        check(!isDone) { "이미 턴이 끝난 상태입니다." }
    }

    private fun outcome(dealer: Dealer): PlayerOutcome = PlayerOutcome.of(this, dealer)

    private fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }

    private fun done(reasonDone: PlayerReasonDone) {
        this.reasonDone = reasonDone
    }
}
