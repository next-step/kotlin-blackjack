package blackjack.domain

class PlayerResult(
    val player: Player,
    val gameResult: List<GameResult>
) {
    constructor(player: Player, gameResult: GameResult) : this(player, listOf(gameResult))

    companion object {

        fun ofDealer(dealer: Player, players: Players): PlayerResult {
            return PlayerResult(dealer, players.list.map { getGameResult(dealer, it) })
        }

        fun ofGamePlayers(dealer: Player, players: Players): List<PlayerResult> {
            return players.list.map { ofGamePlayer(dealer, it) }
        }

        fun ofGamePlayer(dealer: Player, player: Player): PlayerResult {
            val playerResult = getGamePlayerResult(dealer, player)
            return PlayerResult(player, playerResult)
        }

        private fun getGamePlayerResult(dealer: Player, player: Player): GameResult {
            return when (getGameResult(dealer, player)) {
                GameResult.WIN -> GameResult.LOSE
                GameResult.LOSE -> GameResult.WIN
                else -> GameResult.DRAW
            }
        }

        private fun getGameResult(dealer: Player, player: Player): GameResult {
            val score = getScore(dealer) - getScore(player)
            return GameResult.of(score)
        }

        private fun getScore(player: Player): Int {
            return if (player.isBurst()) 0 else player.countingCard()
        }
    }
}
