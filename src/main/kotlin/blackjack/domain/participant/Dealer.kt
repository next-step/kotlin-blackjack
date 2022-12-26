package blackjack.domain.participant

import blackjack.domain.card.state.State
import blackjack.domain.participant.state.role.DealerRole

data class Dealer(override val state: State) : DealerRole() {
    init {
        require(state.cards.size() >= NUMBER_OF_STARTING_CARDS) { "딜러는 2장의 카드를 가지고 시작해야 합니다." }
    }

    companion object {
        private const val NUMBER_OF_STARTING_CARDS = 2
        const val STOP_SCORE = 16
    }
}
