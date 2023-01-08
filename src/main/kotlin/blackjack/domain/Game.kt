package blackjack.domain

class Game(val cardDeck: CardDeck = CardDeck()) {

    fun draw(): Card = cardDeck.draw()

    fun distribute(players: List<Player>) {
        repeat(players.size) {
            val player = players[it]
            draw(player = player)
        }
    }

    private fun draw(player: Player, drawCount: Int = 1) {
        repeat(drawCount) {
            player.receive(draw())
        }
    }

    fun init(players: List<Player>) {
        repeat(players.size) {
            val player = players[it]
            draw(player = player, drawCount = INIT_DRAW_CARD_COUNT)
        }
    }
}
