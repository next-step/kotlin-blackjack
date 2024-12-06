package blackjack.domain

class Game(playerNames: List<String>) {
    private val deck = Deck()
    val players: List<Player> = playerNames.map { Player(it) }

    init {
        players.forEach { it.addCards(deck.drawCards(2)) }
    }

    fun canContinue(player: Player): Boolean {
        return player.score <= 21
    }

    fun drawCardForPlayer(player: Player) {
        player.addCards(deck.drawCards(1))
    }

    fun handlePlayerTurn(player: Player, input: (String) -> String) {
        while (canContinue(player)) {
            if (input(player.name) != "y") break
            drawCardForPlayer(player)
        }
    }
}
