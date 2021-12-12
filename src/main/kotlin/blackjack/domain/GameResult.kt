package blackjack.domain

import blackjack.domain.state.GameResultState

@JvmInline
value class GameResult private constructor(val gameResultMap: Map<GameResultState, Int>) {

    companion object {

        fun makeGameResult(dealer: Dealer, players: Players): GameResult {
            return players.players
                .map {
                    dealer.match(it)
                }
                .groupingBy { it }
                .eachCount()
                .let { from(it) }
        }

        fun from(resultMap: Map<GameResultState, Int>): GameResult {
            return GameResult(resultMap.toMap())
        }
    }
}
