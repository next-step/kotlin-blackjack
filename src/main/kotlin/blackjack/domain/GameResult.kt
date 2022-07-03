package blackjack.domain

data class GameResult(
    val player: Player,
    val result: Result
) {
    companion object {
        fun of(gamePlayers: GamePlayers): List<GameResult> {
            val dealer = gamePlayers.dealer
            val players = gamePlayers.players
            return when (dealer.getStatus()) {
                Status.BLACKJACK -> dealerIsBlackjack(players)
                Status.BUST -> dealerIsBust(players)
                Status.HIT -> dealerIsHit(players, dealer)
            }
        }

        private fun dealerIsBlackjack(players: List<Player>): List<GameResult> =
            players.map {
                when (it.getStatus()) {
                    Status.BLACKJACK -> GameResult(it, Result.DRAW)
                    else -> GameResult(it, Result.LOOSE)
                }
            }

        private fun dealerIsBust(players: List<Player>): List<GameResult> =
            players.map {
                when (it.getStatus()) {
                    Status.BUST -> GameResult(it, Result.LOOSE)
                    else -> GameResult(it, Result.WIN)
                }
            }

        private fun dealerIsHit(players: List<Player>, dealer: Dealer): List<GameResult> =
            players.map { player ->
                when (player.getStatus()) {
                    Status.BLACKJACK -> GameResult(player, Result.WIN)
                    Status.BUST -> GameResult(player, Result.LOOSE)
                    Status.HIT -> {
                        compareHitResult(player, dealer)
                    }
                }
            }

        private fun compareHitResult(player: Player, dealer: Dealer) =
            player.compareScore(dealer).let {
                when {
                    it == 0 -> GameResult(player, Result.DRAW)
                    it > 0 -> GameResult(player, Result.WIN)
                    else -> GameResult(player, Result.LOOSE)
                }
            }
    }
}

enum class Result(val display: String) {
    DRAW("무승부"),
    WIN("승"),
    LOOSE("패");
}
