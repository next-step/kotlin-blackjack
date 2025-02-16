package blackjack.domain

class Dealer : Participant("딜러") {
    override fun isDealer() = true

    override fun canNotReceiveCard(): Boolean {
        return score > MAXIMUM_SCORE_TO_RECEIVE_CARD
    }

    fun determineResult(gamblers: List<Gambler>): BlackjackResults {
        val gamblerResults = determineGamblerResults(gamblers)
        val dealerResult = determineDealerResult(gamblerResults)
        return BlackjackResults(dealerResult, gamblerResults)
    }

    private fun determineGamblerResults(gamblers: List<Gambler>): List<GamblerResult> {
        val gamblerResults = gamblers.map { gambler ->
            val resultStatus = ResultStatus.of(gambler, this)
            GamblerResult(gambler, gambler.calculateProfit(resultStatus.profitRate))
        }
        return gamblerResults
    }

    private fun determineDealerResult(gamblerResults: List<GamblerResult>): DealerResult {
        val profit = gamblerResults.sumOf { gamblerResult -> gamblerResult.profit }
            .unaryMinus()

        return DealerResult(this, profit)
    }

    companion object {
        const val MAXIMUM_SCORE_TO_RECEIVE_CARD = 16
    }
}
