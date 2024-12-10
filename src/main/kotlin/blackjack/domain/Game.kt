package blackjack.domain

class Game(playerNames: List<String>) {
    private val deck = Deck()
    val dealer = Dealer(deck)
    val players: List<Player> = playerNames.map { Player(it) }

    init {
        dealer.initialDraw()
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun handlePlayerTurn(
        player: Player,
        input: (String) -> String,
    ) {
        while (player.canContinue()) {
            if (input(player.name) != "y") break
            player.addCards(deck.drawCards(1))
        }
    }

    fun handleDealerTurn(): Boolean {
        while (dealer.shouldDrawCard()) {
            dealer.drawCard()
        }
        return dealer.canContinue()
    }

    fun determineResults(): Map<String, String> {
        val results = mutableMapOf<String, String>()

        players.forEach { player ->
            results[player.name] = player.compareWithDealer(dealer)
        }

        return results
    }

    fun calculateDealerResult(results: Map<String, String>): String {
        val wins = results.values.count { it == GameResult.LOSE.getResult() }
        val losses = results.size - wins
        return "${wins}${GameResult.WIN.getResult()} ${losses}${GameResult.LOSE.getResult()}"
    }
}
