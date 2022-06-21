package blackjack.domain

@JvmInline
value class BlackjackGameResult private constructor(val value: List<BlackjackParticipantResult>) {
    companion object {
        fun from(participants: Participants): BlackjackGameResult {
            val playerResults = participants.players.toResults(participants.dealer)
            val dealerResult = participants.dealer.toResult(playerResults)

            val results = mutableListOf(dealerResult).apply {
                addAll(playerResults)
            }

            return BlackjackGameResult(results)
        }

        private fun List<Player>.toResults(dealer: Dealer): List<BlackjackParticipantResult> {
            return map { player ->
                BlackjackParticipantResult(
                    participant = player,
                    matchStatus = player matchWith dealer
                )
            }
        }

        private fun Dealer.toResult(playerResults: List<BlackjackParticipantResult>): BlackjackParticipantResult {
            val dealerMatchStatusMap = playerResults.map { playerResult ->
                playerResult.matchStatus.inverse()
            }.groupingBy { matchStatus ->
                matchStatus
            }.eachCount()

            return BlackjackParticipantResult(
                participant = this,
                matchStatus = MatchStatus.Dealer.from(dealerMatchStatusMap)
            )
        }
    }
}
