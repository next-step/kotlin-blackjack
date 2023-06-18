package blackjack.domain.game.result

class ParticipantPlayResults(
    private val dealerPlayResult: ParticipantPlayResult,
    private val playerPlayResults: List<ParticipantPlayResult>,
) {

    val participantPlayResults: List<ParticipantPlayResult> = listOf(element = dealerPlayResult) + playerPlayResults

    fun totalMatchResult(): MatchResults {
        val playerMatchResults = playerPlayResults.map {
            createMatchResult(participantPlayResult = it)
        }

        val dealerMatchResult = MatchResult(
            participant = dealerPlayResult.participant,
            winScore = playerMatchResults.filterIsInstance<LoseResult>().count(),
            loseScore = playerMatchResults.filterIsInstance<WinResult>().count(),
        )

        return MatchResults(
            matchResults = listOf(element = dealerMatchResult) + playerMatchResults,
        )
    }

    private fun createMatchResult(
        participantPlayResult: ParticipantPlayResult,
    ) = if (participantPlayResult.isWinner(otherFinishState = participantPlayResult.finishState)) {
        WinResult(participant = participantPlayResult.participant)
    } else {
        LoseResult(participant = participantPlayResult.participant)
    }
}
