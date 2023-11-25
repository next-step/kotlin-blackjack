package blackjack.model

import blackjack.model.playable.Playable
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player
import blackjack.model.result.ParticipantResults
import blackjack.model.result.PlayerResult
import blackjack.model.result.PlayersResults

object Referee {

    private const val BLACK_JACK_SCORE: Int = 21

    fun isBurst(playable: Playable): Boolean {
        return playable.score() >= BLACK_JACK_SCORE
    }

    fun blackJackResult(participants: Participants): ParticipantResults {
        if (isBlackJackScoreOver(participants.dealer)) {
            // 1) 딜러가 21을 넘어 모든 플레이어가 승리한 상황
            return ParticipantResults.ofDealerLose(participants)
        }
        val playerResults = getPlayerResults(participants)
        return ParticipantResults(
            playerResults = playerResults,
            // dealerDealerResult = getDealerResult(participants, playerResults)
            dealerDealerResult = participants.dealer.result(playerResults)
        )
        // 2) 딜러가 승리한 상황
        // 3) 플레이어중 하나가 승리하고, 딜러가 패배한 상황
        // 4) 플러이어중 하나 승리, 하나 비긴, 하나 패배 딜러
    }

    private fun getPlayerResults(participants: Participants): PlayersResults {
        val dealerScore = participants.dealer.score()
        return PlayersResults(participants.players.scoreBattle(dealerScore))
    }

    private fun isBlackJackScoreOver(dealer: Dealer): Boolean {
        return dealer.score() > BLACK_JACK_SCORE
    }

    fun playerResult(player: Player, dealerScore: Int): PlayerResult {
        if (player.score() == dealerScore) {
            return PlayerResult.DRAW
        }
        if (player.score() > dealerScore) {
            return PlayerResult.WIN
        }
        return PlayerResult.LOSE
    }
}
