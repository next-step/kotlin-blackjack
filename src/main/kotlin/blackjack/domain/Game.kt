package blackjack.domain

class Game(private val playerNames: List<String>) {
    private val deck = Deck()
    val dealer = Dealer(deck)
    val players: List<Player> = playerNames.map { Player(it) }

    init {
        dealer.initialDraw()
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun getInitialState(): Pair<Card, List<Pair<String, List<Card>>>> {
        return dealer.cards[0] to players.map { it.name to it.cards }
    }

    fun start(decisionMaker: PlayerDecision): Pair<Map<String, String>, Boolean> {
        players.forEach { handlePlayerTurn(it, decisionMaker) }
        val dealerDrewCard: Boolean = handleDealerTurn()
        val results = determineResults()
        return results to dealerDrewCard
    }

    private fun handlePlayerTurn(player: Player, decisionMaker: PlayerDecision) {
        while (player.canContinue()) {
            if (!decisionMaker.shouldDrawCard(player.name)) break
            player.addCards(deck.drawCards(1))
        }
    }

    private fun handleDealerTurn(): Boolean {
        var drewCard = false
        while (dealer.shouldDrawCard()) {
            dealer.drawCard()
            drewCard = true
        }
        return drewCard
    }

    fun determineResults(): Map<String, String> {
        return players.associate { player ->
            player.name to player.compareWithDealer(dealer)
        }
    }

    fun calculateDealerResult(results: Map<String, String>): String {
        val wins = results.values.count { it == GameResult.LOSE.getResult() }
        val losses = results.size - wins
        return "${wins}${GameResult.WIN.getResult()} ${losses}${GameResult.LOSE.getResult()}"
    }

    fun getFinalScores(): Pair<List<Pair<String, Int>>, Pair<List<Card>, Int>> {
        val playerScores = players.map { it.name to it.score }
        val dealerState = dealer.cards to dealer.score
        return playerScores to dealerState
    }
}
