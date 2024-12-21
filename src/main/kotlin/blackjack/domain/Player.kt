package blackjack.domain

import java.math.BigDecimal

class Player(
    val name: String,
    state: State = Dealing(Hand()),
) {
    var state: State = state
        private set
    val hand: Hand get() = state.hand
    val value: Int get() = state.hand.value()
    val isBlackjack: Boolean get() = state is Blackjack
    val isBusted: Boolean get() = state is Busted
    val isDone: Boolean get() = state is Finished
    lateinit var bet: Bet
        private set

    init {
        require(name.isNotBlank()) { "이름이 빈 문자열입니다." }
    }

    fun initialDrawFrom(deck: Deck) {
        state = state.drawFrom(deck)
    }

    fun hit(deck: Deck) {
        state = state.drawFrom(deck)
    }

    fun stand() {
        state = state.stand()
    }

    fun placeBet(bet: Bet) {
        this.bet = bet
    }

    fun dealerDealtBlackjack() {
        stand()
    }

    fun result(dealer: Dealer): PlayerResult {
        checkStateIsFinished()
        checkBetIsNotNull()
        val outcome = PlayerOutcome.of(this, dealer)
        return PlayerResult(name, profit(bet, outcome))
    }

    private fun profit(
        bet: Bet,
        outcome: PlayerOutcome,
    ) = when (outcome) {
        PlayerOutcome.WIN -> state.profit(bet)
        PlayerOutcome.LOSE -> bet.value.negate()
        PlayerOutcome.DRAW -> BigDecimal.ZERO
    }

    fun pushes(dealer: Dealer): Boolean = hand.pushes(dealer.hand)

    fun beats(dealer: Dealer): Boolean = hand.beats(dealer.hand)

    private fun checkStateIsFinished() {
        check(state is Finished) { "플레이어의 턴이 종료되지 않았습니다." }
    }

    private fun checkBetIsNotNull() {
        check(::bet.isInitialized) { "베팅이 없습니다." }
    }
}
