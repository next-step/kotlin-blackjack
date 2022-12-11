package blackjack.domain

class Casino(val players: List<Player>) {

    private val dealer = Dealer()

    fun distribute() {
        repeat(players.size) {
            val player = players[it]
            repeat(INITIAL_CARD_DECK_SIZE) { player.receive(dealer.draw()) }
        }
    }

    fun draw(player: Player) {
        player.receive(dealer.draw())
    }

    companion object {
        private const val INITIAL_CARD_DECK_SIZE = 2
    }
}
