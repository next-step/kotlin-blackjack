package blackjack.domain.state

object DealerBurst : OutcomeState(gamerRate = 1.0, dealerRate = -1.0) {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType != StateType.BLACKJACK && dealerType == StateType.BURST
}
