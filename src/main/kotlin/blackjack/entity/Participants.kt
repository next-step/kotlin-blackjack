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
        return dealer.playTurn(deck) == PlayerAction.DRAW
    }

    fun calculateResult(): List<GameResult> {
        val dealerScore = dealer.calculateScore()
        val playerResults = players.map { it.calculateResult(dealerScore) }
        val dealerResult = dealer.calculateResult(playerResults)
        return listOf(dealerResult) + playerResults
    }
}
