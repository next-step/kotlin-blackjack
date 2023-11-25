package blackjack.model

import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableResult
import blackjack.model.playable.impl.Player
import blackjack.model.result.ParticipantResults
import blackjack.model.result.PlayersResults

object Referee {

    const val BLACK_JACK_SCORE: Int = 21

    fun isBurst(playable: Playable): Boolean {
        return playable.score() >= BLACK_JACK_SCORE
    }

    fun blackJackResult(participants: Participants): ParticipantResults {
        if (participants.dealer.isBurst()) {
            return ParticipantResults.ofDealerLose(participants)
        }
        val playerResults = getPlayerResults(participants)
        return ParticipantResults(
            playerResults = playerResults,
            dealerDealerResult = participants.dealer.dealerResult(participants.players)
        )
        // 2) 딜러가 승리한 상황
        // 3) 플레이어중 하나가 승리하고, 딜러가 패배한 상황
        // 4) 플러이어중 하나 승리, 하나 비긴, 하나 패배 딜러
    }

    private fun getPlayerResults(participants: Participants): PlayersResults {
        val dealerScore = participants.dealer.score()
        return PlayersResults(participants.players.scoreBattle(dealerScore))
    }


    fun playerResult(player: Player, dealerScore: Int): PlayableResult {
        if (player.score() == dealerScore) {
            return PlayableResult.DRAW
        }
        if (player.score() > dealerScore) {
            return PlayableResult.WIN
        }
        return PlayableResult.LOSE
    }
}
