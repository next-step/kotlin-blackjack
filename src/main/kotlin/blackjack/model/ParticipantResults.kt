package blackjack.model

class ParticipantResults(
    val playerResults: Map<Player, PlayerResult>,
    val dealerDealerResult: Pair<Dealer, DealerResult>,
) {
    fun dealerResult(): DealerResult {
        return dealerDealerResult.second
    }

    fun playerResult(player: Player): PlayerResult {
        return requireNotNull(playerResults[player]) {"플레이어를 찾을 수 없음"}
    }

    companion object {
        fun ofDealerLose(participants: Participants): ParticipantResults {
            return ParticipantResults(
                participants.players.associate { this.playerWin(it, participants) },
                participants.dealer to DealerResult(participants.dealer.score(), 0, participants.count() - 1)
            )
        }

        private fun playerWin(player: Player, participants: Participants): Pair<Player, PlayerResult> {
            return player to PlayerResult.WIN
        }

    }
}
