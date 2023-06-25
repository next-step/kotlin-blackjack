package blackjack.domain

private const val START_CARD_COUNT = 2

class BlackJack(val players: List<Player>) {
    fun start() {
        for (i in 0 until START_CARD_COUNT) players.forEach { draw(it) }
    }

    private fun draw(player: Player) {
        var card = Card.draw()
        while (players.find { it.contains(card) } != null) {
            card = Card.draw()
        }
        player.addCard(card)
    }
}