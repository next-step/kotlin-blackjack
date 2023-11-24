package blackjack.model.result

import blackjack.model.Participants
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player

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

    fun dealerWinCount(): Int {
        return this.dealerDealerResult.second.winning
    }

    fun dealerLoseCount(): Int {
        return this.dealerDealerResult.second.losing
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
