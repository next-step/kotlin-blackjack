package blackjack.participant

import blackjack.result.ParticipantResult

data class Participants(
    val dealer: Dealer,
    val gamePlayers: Players,
) {
    fun getDealerRate(): ParticipantResult {
        val gameResults = gamePlayers.players.map { player ->
            when {
                dealer.score() > player.getScore() -> GameResult.WIN
                dealer.score() < player.getScore() -> GameResult.LOSE
                else -> GameResult.DRAW
            }
        }
        return ParticipantResult(dealer.getName(), gameResults)
    }

    fun getPlayersRate(): List<ParticipantResult> {
        return gamePlayers.players.map { player ->
            ParticipantResult(
                player.getName(),
                listOf(
                    when {
                        player.getScore() > dealer.score() -> GameResult.WIN
                        player.getScore() < dealer.score() -> GameResult.LOSE
                        else -> GameResult.DRAW
                    },
                ),
            )
        }
    }
}
