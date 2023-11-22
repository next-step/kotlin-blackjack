package blackjack.model

object Referee {

    private const val BLACK_JACK_SCORE: Int = 21

    fun isGameOver(playable: Playable): Boolean {
        return playable.score() >= BLACK_JACK_SCORE
    }


    fun blackJackResult(participants: Participants): ParticipantResults {
        if (isBlackJackScoreOver(participants.dealer)) {
            // 1) 딜러가 21을 넘어 모든 플레이어가 승리한 상황
            ParticipantResults.ofDealerLose(participants)
        }
        if (isWinDealer()) {
            // 2) 딜러가 승리한 상황
            //return ParticipantResults
            TODO("딜러가 이긴상황")
        }
        // 3) 플레이어중 하나가 승리하고, 딜러가 패배한 상황
        TODO("플레이어가 이긴상황")
        //return ParticipantResults.of(participants)
    }

    private fun isWinDealer(): Boolean {
        TODO("Not yet implemented")
    }

    private fun isBlackJackScoreOver(dealer: Dealer): Boolean {
        return dealer.score() > BLACK_JACK_SCORE
    }
}
