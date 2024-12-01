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

    fun calculateResult(): List<GameResult> {
        val dealerScore = ComparisonScore.Single(dealer.calculateScore())
        val playerScores = ComparisonScore.Multiple(players.map { it.calculateScore() })
        val dealerResult = dealer.calculateResult(playerScores)
        val playerResults = players.map { it.calculateResult(dealerScore) }
        return listOf(dealerResult) + playerResults
    }
}
