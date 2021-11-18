package blackjack.model

sealed class GameResult(val gamer: Gamer) {

    data class Count(val win: Int, val lose: Int, val push: Int)

    class Dealer(gamer: Gamer, val count: Count) : GameResult(gamer)

    class Win(gamer: Gamer) : GameResult(gamer)

    class Lose(gamer: Gamer) : GameResult(gamer)

    // 무승부
    class Push(gamer: Gamer) : GameResult(gamer)

    companion object {
        private const val TWENTY_ONE = 21

        fun match(gamers: Gamers): List<GameResult> {
            val dealer = gamers.dealer() ?: return emptyList()
            val players = gamers.players()
            val dealerScore = dealer.score
            val playerResults = players.map { player ->
                val score = player.score
                when {
                    dealerScore > TWENTY_ONE && score <= TWENTY_ONE -> Win(player)
                    score > TWENTY_ONE || score < dealerScore -> Lose(player)
                    score > dealerScore -> Win(player)
                    else -> Push(player)
                }
            }
            val count = Count(
                win = playerResults.count { it is Lose },
                lose = playerResults.count { it is Win },
                push = playerResults.count { it is Push }
            )
            val dealerResult = Dealer(dealer, count)
            return listOf(dealerResult) + playerResults
        }
    }
}
