package blackjack.domain.state

object DealerBurst : OutcomeState() {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType != StateType.BLACKJACK && dealerType == StateType.BURST

    override fun getGamerRate(): Double = 1.0

    override fun getDealerRate(): Double = -1.0
}
