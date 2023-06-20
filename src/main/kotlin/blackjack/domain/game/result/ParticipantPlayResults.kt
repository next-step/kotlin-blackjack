package blackjack.domain.game.result

class ParticipantPlayResults(
    private val dealerPlayResult: ParticipantPlayResult,
    private val playerPlayResults: List<ParticipantPlayResult>,
) {

    val participantPlayResults: List<ParticipantPlayResult> = listOf(element = dealerPlayResult) + playerPlayResults

    fun totalMatchResult(): MatchResults {
        val playerMatchResults = playerPlayResults.map {
            val participant = it.participant

            MatchResult(
                participant = participant,
                betResultAmount = it.finishState.profit(
                    betAmount = participant.bettingAmount(),
                    otherState = dealerPlayResult.finishState,
                ),
            )
        }

        val dealerMatchResult = MatchResult(
            participant = dealerPlayResult.participant,
            betResultAmount = playerMatchResults.sumOf { it.betResultAmount }.unaryMinus(),
        )

        return MatchResults(
            matchResults = listOf(element = dealerMatchResult) + playerMatchResults,
        )
    }
}
