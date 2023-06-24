package blackjack.domain.state

object Push : OutcomeState() {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BLACKJACK && dealerType == StateType.BLACKJACK

    override fun getGamerRate(): Double = 0.0

    override fun getDealerRate(): Double = 0.0
}
