package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class BlackJackResult(
    val playerResult: List<GamerResult>,
    val dealerResult: List<GamerResult>,
) {

    fun getDealerStatistics(): DealerResult {
        val count = dealerResult.groupingBy { it.result }
            .eachCount()
        return with(count) {
            DealerResult(
                win = getOrDefault(GameResult.Type.WIN),
                draw = getOrDefault(GameResult.Type.DRAW),
                lose = getOrDefault(GameResult.Type.LOSE),
            )
        }
    }

    private fun Map<GameResult.Type, Int>.getOrDefault(type: GameResult.Type): Int {
        return get(type) ?: DEFAULT_COUNT
    }

    companion object {

        private const val DEFAULT_COUNT = 0

        fun from(dealer: Dealer, players: List<Player>): BlackJackResult {
            val gamerResults = players.map {
                GamerResultFactory.getGamerResult(dealer, it)
            }
            val playerResults = gamerResults.map { it.playerResult }
            val dealerResults = gamerResults.map { it.dealerResult }
            return BlackJackResult(playerResult = playerResults, dealerResult = dealerResults)
        }
    }
}

