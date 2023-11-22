package blackjack.model

class ParticipantResults(
    val playerResults: Set<Pair<Player, PlayerResult>>,
    val dealerDealerResult: Pair<Dealer, DealerResult>,
) {
    fun dealerResult(): DealerResult {
        return dealerDealerResult.second
    }

    fun findPlayerResult(player: Player): DealerResult {
        TODO()
    }

    companion object {
        fun ofDealerLose(participants: Participants): ParticipantResults {
            return ParticipantResults(
                participants.players.map { this.playerWin(it, participants) }.toSet(),
                participants.dealer to DealerResult(participants.dealer.score(), 0, participants.count() - 1)
            )
        }

        private fun playerWin(player: Player, participants: Participants): Pair<Player, PlayerResult> {
            return player to PlayerResult.WIN
        }

        fun of(participants: Participants) {
            ParticipantResults
        }
    }
}
