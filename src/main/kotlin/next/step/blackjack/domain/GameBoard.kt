package next.step.blackjack.domain

data class GameBoard(val gameCards: GameCards, val players: Set<Player>) {
    fun start(announce: (Set<Player>, Int) -> Unit) {
        players.forEach { player -> repeat(INIT_CARD_CNT) { hit(player) } }
        announce(players, INIT_CARD_CNT)
    }

    private fun hit(player: Player) {
        player.hit(gameCards.pop())
    }

    fun turn(chooseHit: (Player) -> Boolean, announce: (Player) -> Unit) {
        players.forEach {
            while (it.canHit() && chooseHit(it)) {
                hit(it)
                announce(it)
            }
        }
    }

    fun finish(announce: (Set<Player>) -> Unit) {
        announce(players)
    }

    companion object {
        private const val INIT_CARD_CNT = 2
        fun of(gameCards: GameCards, players: Set<Player>): GameBoard = GameBoard(gameCards, players)
    }
}
