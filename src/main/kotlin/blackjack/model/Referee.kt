package blackjack.model

import blackjack.model.playable.Playable

object Referee {

    const val BLACK_JACK_SCORE: Int = 21

    fun isBurst(playable: Playable): Boolean {
        return playable.score() >= BLACK_JACK_SCORE
    }

//    fun blackJackResult(participants: Participants): DealerResult {
//        if (participants.dealer.isBurst()) {
//            return DealerResult.burst(participants.dealer, participants)
//        }
//
//    }

    // val playerResults = getPlayerResults(participants)

//    private fun getPlayerResults(participants: Participants): PlayersResults {
//        val dealerScore = participants.dealer.score()
//        return PlayersResults(participants.players.scoreBattle(dealerScore))
//    }
//
//
//    fun playerResult(player: Player, dealerScore: Int): PlayableResult {
//        if (player.score() == dealerScore) {
//            return PlayableResult.DRAW
//        }
//        if (player.score() > dealerScore) {
//            return PlayableResult.WIN
//        }
//        return PlayableResult.LOSE
//    }
}
