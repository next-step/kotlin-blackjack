package blackjack.domain

class Game(
    private val playersInfo: List<PlayerInfo>,
) {
    private val deck = Deck()
    val dealer: Dealer = Dealer(deck, playersInfo.find { it.name == "딜러" }?.bet ?: 0)
    val players: List<Player> = playersInfo
        .filter { it.name != "딜러" }
        .map { Player(it.name, it.bet) }

    init {
        dealer.initialDraw()
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun getInitialState(): Pair<Card, List<Pair<String, List<Card>>>> {
        return dealer.cards[0] to players.map { it.name to it.cards }
    }

    fun start(decisionMaker: PlayerDecision): Pair<Map<String, GameResult>, Boolean> {
        players.forEach { handlePlayerTurn(it, decisionMaker) }
        val dealerDrewCard = handleDealerTurn()

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
            player.name to GameRules.determineResult(player, dealer)
        }
    }

    fun calculateProfits(): Map<String, Int> {
        val dealerProfit = players.sumOf { player ->
            when (GameRules.determineResult(player, dealer)) {
                GameResult.WIN -> -player.bet
                GameResult.LOSE -> player.bet
                else -> 0
            }
        }

        val playerProfits = players.associate { player ->
            player.name to when (GameRules.determineResult(player, dealer)) {
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
