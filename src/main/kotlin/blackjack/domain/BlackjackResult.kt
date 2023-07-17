package blackjack.domain

class BlackjackResult(val dealer: Dealer, val players: List<Player>) {
    val dealerResult: DealerResult
    val playerResults: List<PlayerResult>

    init {
        val resultsOnDealer = mutableListOf<Result>()

        playerResults = players.map {
            val result  = dealer.calculatePlayerResult(it)
            resultsOnDealer.add(result.getOpposite())
            PlayerResult(it, result)
        }

        dealerResult = DealerResult(dealer, resultsOnDealer)
    }
}