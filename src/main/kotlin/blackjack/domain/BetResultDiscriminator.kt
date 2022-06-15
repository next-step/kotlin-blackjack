package blackjack.domain

object BetResultDiscriminator {
    fun discrimination(dealer: Dealer, players: Players): List<BetResult> {
        val playerResults = players.getBetResults(dealer)
        val dealerResult = BetResult(dealer, -playerResults.sumOfEarnMoney())
        return listOf(dealerResult) + playerResults
    }
}
