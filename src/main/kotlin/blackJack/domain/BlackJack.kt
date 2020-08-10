package blackJack.domain

class BlackJack(names: List<String>) {
    val players = makePlayer(names)
    private val deck = Deck()

    init {
        readyGame()
        deck.shuffle()
    }

    private fun makePlayer(names: List<String>): List<Player> = names.map { Player(it) }

    private fun readyGame() {
        (0..1).map { giveCardAllPlayer() }
    }

    private fun giveCardAllPlayer() {
        players.forEach { giveCardPlayer(it) }
    }

    private fun giveCardPlayer(player: Player) {
        player.giveCard(deck.getCard())
    }
}
