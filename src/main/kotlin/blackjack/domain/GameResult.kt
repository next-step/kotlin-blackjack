package blackjack.domain

import blackjack.domain.player.User

data class GameResult(val players: Map<User, ResultType>) {
    val dealer: Map<ResultType, Int>
        get() {
            return mapOf(
                Pair(ResultType.WIN, players.count { it.value == ResultType.LOSE }),
                Pair(ResultType.DRAW, players.count { it.value == ResultType.DRAW }),
                Pair(ResultType.LOSE, players.count { it.value == ResultType.LOSE }),
            )
        }
}