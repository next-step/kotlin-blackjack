package blackjack.domain

@JvmInline
value class BlackjackGameResult private constructor(val value: List<BlackjackParticipantResult>) {
    companion object {
        fun from(participants: Participants): BlackjackGameResult {
            val playerResults = participants.players.toResults(participants.dealer)
            val dealerResult = participants.dealer.toResult(playerResults)

            return BlackjackGameResult(dealerResult + playerResults)
        }

        private fun List<Player>.toResults(dealer: Dealer): List<BlackjackParticipantResult> {
            return map { player ->
                val matchStatus = player matchWith dealer
                BlackjackParticipantResult(
                    participant = player,
                    revenue = matchStatus.getRevenueFrom(player.betAmount)
                )
            }
        }

        private fun Dealer.toResult(playerResults: List<BlackjackParticipantResult>): List<BlackjackParticipantResult> {
            val playersRevenue = playerResults.sumOf { result ->
                result.revenue.value
            }

            return listOf(
                BlackjackParticipantResult(
                    participant = this,
                    revenue = Revenue(playersRevenue).reverse()
                )
            )
        }
    }
}
