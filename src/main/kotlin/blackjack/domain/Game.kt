package blackjack.domain

class Game(playerNames: List<String>) {
    private val deck = Deck()
    val dealer = Dealer(deck)
    val players: List<Player> = playerNames.map { Player(it) }

    init {
        dealer.initialDraw()
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun canContinue(player: Player): Boolean {
        return player.score <= 21
    }

    fun handlePlayerTurn(
        player: Player,
        input: (String) -> String,
    ) {
        while (canContinue(player)) {
            if (input(player.name) != "y") break
            player.addCards(deck.drawCards(1))
        }
    }

    fun handleDealerTurn(): Boolean {
        if (dealer.shouldDrawCard()) {
            dealer.drawCard()
            return true
        }
        return false
    }

    fun determineResults(): Map<String, String> {
        val results = mutableMapOf<String, String>()
        if (dealer.score > 21) {
            players.forEach { results[it.name] = "승" }
            return results
        }

        players.forEach { player ->
            results[player.name] =
                when {
                    player.score > 21 || dealer.score > player.score -> "패"
                    player.score == dealer.score -> "무승부"
                    else -> "승"
                }
        }
        return results
    }

    fun calculateDealerResult(results: Map<String, String>): String {
        val wins = results.values.count { it == "패" }
        val losses = results.size - wins
        return "${wins}승 ${losses}패"
    }
}
