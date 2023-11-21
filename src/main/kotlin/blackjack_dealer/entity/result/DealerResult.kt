package blackjack_dealer.entity.result

import blackjack_dealer.entity.state.ParticipantResultState

data class DealerResult(
    val name: String = "딜러",
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val drawCount: Int = 0,
) {
    companion object {
        private const val DEFAULT_VALUE = 0
        fun newInstance(winCount: Int, loseCount: Int, drawCount: Int): DealerResult {
            return DealerResult(winCount = winCount, loseCount = loseCount, drawCount = drawCount)
        }

        fun createDealerResultFromParticipantResult(participantResultCounts: Map<ParticipantResultState, Int>): DealerResult {
            return newInstance(
                winCount = participantResultCounts.getOrDefault(
                    ParticipantResultState.LOSE, DEFAULT_VALUE
                ), loseCount = participantResultCounts.getOrDefault(
                    ParticipantResultState.WIN, DEFAULT_VALUE
                ), drawCount = participantResultCounts.getOrDefault(
                    ParticipantResultState.DRAW, DEFAULT_VALUE
                )
            )
        }
    }
}
