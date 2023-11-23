package blackjack.model

class ParticipantResults(
    val playerResults: PlayersResults,
    val dealerDealerResult: Pair<Dealer, DealerResult>,
) {
    fun dealerResult(): DealerResult {
        return dealerDealerResult.second
    }

    fun playerResult(player: Player): PlayerResult {
        return playerResults.resultOfPlayer(player)
    }

    companion object {
        fun ofDealerLose(participants: Participants): ParticipantResults {
            return ParticipantResults(
                PlayersResults(participants.players.walkover()),
                participants.dealer to DealerResult(participants.dealer.score(), 0, 0, participants.count() - 1)
            )
        }
    }
}
