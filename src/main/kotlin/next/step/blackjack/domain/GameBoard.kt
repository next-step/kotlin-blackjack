package next.step.blackjack.domain

data class GameBoard(val gameCards: GameCards, val players: Set<Player>) {
    fun start(announce: (Set<Player>) -> Unit) {
        players.forEach { player -> repeat(2) { hit(player) } }
        announce(players)
    }

    private fun hit(player: Player) {
        player.hit(gameCards.pop())
    }

    fun turn(chooseHit: (Player) -> Boolean, announce: (Player) -> Unit) {
        players.forEach {
            while (chooseHit(it) && it.canHit()) {
                hit(it)
                announce(it)
            }
        }
    }

    fun finish(announce: (Set<Player>) -> Unit) {
        announce(players)
    }

    companion object {
        fun of(gameCards: GameCards, players: Set<Player>): GameBoard = GameBoard(gameCards, players)
    }
}