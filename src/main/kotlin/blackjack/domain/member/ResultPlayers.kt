package blackjack.domain.member

import blackjack.domain.GameState

class ResultPlayers(
    val items: List<ResultPlayer>
) {

    operator fun plus(addPlayers: ResultPlayers): ResultPlayers {
        return ResultPlayers(this.items + addPlayers.items)
    }

    fun toWinnerPlayers(): ResultPlayers {
        return ResultPlayers(
            items.map { ResultPlayer(it.player, GameState.WIN) }
        )
    }
}
