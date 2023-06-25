package blackjack.domain

class BlackJack(private val players: List<Player>) {
    fun start() {
        for (i in 0 until 2) players.forEach { draw(it) }
    }

    private fun draw(player: Player) {
        var card = Card.draw()
        while (players.find { it.contains(card) } != null) {
            card = Card.draw()
        }
        player.addCard(card)
    }
}