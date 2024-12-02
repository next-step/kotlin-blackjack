package blackjack.entity

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun initializeHands(deck: Deck) {
        repeat(2) { dealer.receiveCard(deck.deal()) }
        players.forEach { player ->
            repeat(2) { player.receiveCard(deck.deal()) }
        }
    }

    fun playDealerTurn(deck: Deck): Boolean {
        return if (dealer.shouldDrawCard()) {
            dealer.receiveCard(deck.deal())
            true
        } else {
            false
        }
    }

    fun calculateResult(): List<GameResult> {
        val dealerScore = dealer.calculateScore()
        val playerScores = players.map { it.calculateScore() }
        val dealerResult = dealer.calculateResult(ComparisonScore.Players(playerScores))
        val playerResults = players.map { it.calculateResult(ComparisonScore.Dealer(dealerScore)) }
        return listOf(dealerResult) + playerResults
    }
}
