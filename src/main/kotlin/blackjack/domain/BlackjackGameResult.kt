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
                BlackjackParticipantResult(
                    participant = player,
                    revenue = player getRevenueFrom dealer
                )
            }
        }

        private fun Dealer.toResult(playerResults: List<BlackjackParticipantResult>): List<BlackjackParticipantResult> {
            return listOf(
                BlackjackParticipantResult(
                    participant = this,
                    revenue = playerResults.getDealerRevenue()
                )
            )
        }
    }
}
