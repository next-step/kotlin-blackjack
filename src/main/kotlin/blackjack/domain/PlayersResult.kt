package blackjack.domain

import blackjack.domain.state.GameResultState

@JvmInline
value class PlayersResult(val playersResultMap: Map<Name, GameResultState>) {

    companion object {

        fun makePlayersResult(dealer: Dealer, players: Players): PlayersResult {
            return players
                .players
                .map { it.name to dealer.match(it) }
                .toMap()
                .let { from(it) }
        }

        fun from(resultMap: Map<Name, GameResultState>): PlayersResult {
            return PlayersResult(resultMap.toMap())
        }
    }
}
