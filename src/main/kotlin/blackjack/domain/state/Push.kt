package blackjack.domain.state

object Push : OutcomeState(gamerRate = 0.0, dealerRate = 0.0) {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BLACKJACK && dealerType == StateType.BLACKJACK
}
