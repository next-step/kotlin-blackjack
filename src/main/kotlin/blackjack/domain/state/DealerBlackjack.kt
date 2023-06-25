package blackjack.domain.state

object DealerBlackjack : OutcomeState(gamerRate = -1.0, dealerRate = 1.0) {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType != StateType.BLACKJACK && dealerType == StateType.BLACKJACK
}
