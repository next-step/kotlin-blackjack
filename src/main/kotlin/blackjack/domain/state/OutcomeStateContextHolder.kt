package blackjack.domain.state

object OutcomeStateContextHolder {
    private val store = listOf(Push, DealerBlackjack, PlayerBlackjack, DealerBurst, PlayerBurst, Stay)

    fun find(playerType: StateType, dealerType: StateType) =
        store.find { it.supported(playerType = playerType, dealerType = dealerType) }
            ?: throw IllegalStateException("유효한 결과 처리기를 찾을 수 없습니다. [player: $playerType, dealer: $dealerType]")
}
