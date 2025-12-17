package domain

class GameResult(
    val winner: Players,
    val loser: Players,
    val drawer: Players,
) {
    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
        ): GameResult {
            val winner: MutableList<Player> = mutableListOf()
            val loser: MutableList<Player> = mutableListOf()
            val drawer: MutableList<Player> = mutableListOf()
            players.players.forEach { player ->
                when {
                    player.cards.isBust() -> loser.add(player)
                    dealer.cards.isBust() -> winner.add(player)
                    player.cards.calculateScore() > dealer.cards.calculateScore() -> winner.add(player)
                    player.cards.calculateScore() < dealer.cards.calculateScore() -> loser.add(player)
                    else -> drawer.add(player)
                }
            }
            return GameResult(Players(winner), Players(loser), Players(drawer))
        }
    }
}
