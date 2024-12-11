package blackjack.domain

class Game(
    private val playerNames: List<String>,
    private val playerBets: Map<String, Int>,
) {
    private val deck = Deck()
    val dealer = Dealer(deck, playerBets["딜러"] ?: 0)
    val players: List<Player> = playerNames.map { Player(it, playerBets[it] ?: 0) }

    init {
        dealer.initialDraw()
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun getInitialState(): Pair<Card, List<Pair<String, List<Card>>>> {
        return dealer.cards[0] to players.map { it.name to it.cards }
    }

    fun start(decisionMaker: PlayerDecision): Pair<Map<String, GameResult>, Boolean> {
        players.forEach { handlePlayerTurn(it, decisionMaker) }
        val dealerDrewCard: Boolean = handleDealerTurn()

        // Check if dealer busts
        if (dealer.score > 21) {
            players.filter { it.score <= 21 }.forEach { it.setResult(GameResult.WIN) }
            return emptyMap<String, GameResult>() to dealerDrewCard
        }

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

    private fun determineResults(): Map<String, GameResult> {
        return players.associate { player ->
            player.name to player.compareWithDealer(dealer).also { result ->
                player.setResult(result)
            }
        }
    }

    fun calculateProfits(): Map<String, Int> {
        val dealerProfit = players.sumOf { player ->
            when (player.result) {
                GameResult.WIN -> -player.bet
                GameResult.LOSE -> player.bet
                else -> 0
            }
        }

        val playerProfits = players.associate { player ->
            player.name to when (player.result) {
                GameResult.WIN -> player.bet
                GameResult.LOSE -> -player.bet
                else -> 0
            }
        }

        return playerProfits + ("딜러" to dealerProfit)
    }

    fun getFinalScores(): Pair<List<Pair<String, Int>>, Pair<List<Card>, Int>> {
        val playerScores = players.map { it.name to it.score }
        val dealerState = dealer.cards to dealer.score
        return playerScores to dealerState
    }
}
