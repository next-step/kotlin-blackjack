package blackjack.domain

class BlackjackGame(
    val deck: Deck,
    val players: List<Player>,
) {

    fun start() {
        players.forEach { player ->
            repeat(START_DRAW_COUNT) { draw(player) }
        }
    }

    fun draw(player: Player) {
        player.receivedCard(deck.draw())
    }

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
