package blackjack.domain

import blackjack.domain.player.User

class GameResult(players: List<User>, dealer: User) {
    val players: Map<User, ResultType> = initPlayerResult(players, dealer)
    val dealer: Map<ResultType, Int>
        get() {
            return mapOf(
                Pair(ResultType.WIN, players.count { it.value == ResultType.LOSE }),
                Pair(ResultType.DRAW, players.count { it.value == ResultType.DRAW }),
                Pair(ResultType.LOSE, players.count { it.value == ResultType.WIN }),
            )
        }

    private fun initPlayerResult(players: List<User>, dealer: User) =
        players.map { Pair(it, it.calculateScore().compareTo(dealer.calculateScore())) }.toMap()
}
