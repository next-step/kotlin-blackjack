package blackjack_dealer.entity.result

import blackjack_dealer.entity.state.ParticipantResultState

data class DealerResult(
    val name: String = "딜러",
    val dealerResultCount: DealerResultCount = DealerResultCount()
) {
    companion object {
        private const val DEFAULT_VALUE = 0
        private fun newInstance(dealerResultCount: DealerResultCount): DealerResult {
            return DealerResult(dealerResultCount = dealerResultCount)
        }

        fun createDealerResultFromParticipantResult(participantResultCounts: Map<ParticipantResultState, Int>): DealerResult {
            val winCount = participantResultCounts.getOrDefault(ParticipantResultState.LOSE, DEFAULT_VALUE)
            val loseCount = participantResultCounts.getOrDefault(ParticipantResultState.WIN, DEFAULT_VALUE)
            val drawCount = participantResultCounts.getOrDefault(ParticipantResultState.DRAW, DEFAULT_VALUE)
            return newInstance(DealerResultCount(win = winCount, lose = loseCount, draw = drawCount))
        }
    }
}
