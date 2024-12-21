package blackjack.domain

class BlackjackResults(participants: Participants) {
    val dealerResult: DealerResult
    val gamblerResults: List<GamblerResult>

    init {
        val dealer = participants.extractDealer()
        val gamblers = participants.extractGamblers()

        gamblerResults = gamblers.map { gambler ->
            val isWin = dealer.isExceedWinScore() || gambler.isWin(dealer)
            GamblerResult(gambler, isWin)
        }

        val winGamblerCount = gamblerResults.count { gamblerResult -> gamblerResult.isWin }
        val defeatGamblerCount = gamblerResults.size - winGamblerCount
        dealerResult = DealerResult(dealer, defeatGamblerCount, winGamblerCount)
    }
}
