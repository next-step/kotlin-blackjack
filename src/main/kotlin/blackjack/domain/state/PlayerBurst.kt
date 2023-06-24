package blackjack.domain.state

object PlayerBurst : OutcomeState() {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BURST && dealerType != StateType.BLACKJACK

    override fun getGamerRate(): Double = -1.0

    override fun getDealerRate(): Double = 1.0
}
