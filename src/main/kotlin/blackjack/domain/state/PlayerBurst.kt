package blackjack.domain.state

object PlayerBurst : OutcomeState(gamerRate = -1.0, dealerRate = 1.0) {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BURST && dealerType != StateType.BLACKJACK
}
