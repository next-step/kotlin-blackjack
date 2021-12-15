package blackjack.domain.result

import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.domain.state.GameResultState

@JvmInline
value class PlayerResult private constructor(val playerResultMap: Map<GameResultState, Int>) {

    companion object {

        fun makeGameResult(dealer: Dealer, players: Players): PlayerResult {
            return players.players
                .map {
                    it.match(dealer)
                }
                .groupingBy { it }
                .eachCount()
                .let { from(it) }
        }

        fun from(resultMap: Map<GameResultState, Int>): PlayerResult {
            return PlayerResult(resultMap.toMap())
        }
    }
}
