package blackjack.domain

class BlackjackResults(participants: Participants) {
    val dealerResult: DealerResult
    val gamblerResults: List<GamblerResult>

    init {
        val dealer = participants.extractDealer()
        val gamblers = participants.extractGamblers()

        gamblerResults = determineGamblerResults(dealer, gamblers)
        dealerResult = DealerResult(dealer, gamblerResults)
    }

    private fun determineGamblerResults(dealer: Dealer, gamblers: List<Gambler>): List<GamblerResult> {
        return gamblers.map { gambler ->
            GamblerResult(gambler, gambler.determineResultStatus(dealer))
        }
    }
}
