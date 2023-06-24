package blackjack.domain.state

object PlayerBlackjack : OutcomeState() {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BLACKJACK && dealerType != StateType.BLACKJACK

    override fun getGamerRate(): Double = 1.5

    override fun getDealerRate(): Double = -1.5
}
