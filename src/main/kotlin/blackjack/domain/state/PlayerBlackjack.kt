package blackjack.domain.state

object PlayerBlackjack : OutcomeState(gamerRate = 1.5, dealerRate = -1.5) {
    override fun supported(playerType: StateType, dealerType: StateType): Boolean =
        playerType == StateType.BLACKJACK && dealerType != StateType.BLACKJACK
}
