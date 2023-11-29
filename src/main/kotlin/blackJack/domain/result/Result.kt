package blackJack.domain.result

import blackJack.domain.player.Participants

class Result(val playersResult: PlayersResult, val dealerResult: DealerResult) {

    companion object {
        fun calculateResult(participants: Participants): Result {
            val playersResults = PlayersResult.calculateResult(participants.players, participants.dealer)
            val dealerResult = DealerResult.calculateResult(playersResults)
            return Result(playersResults, dealerResult)
        }
    }
}
