package blackjack.domain.result

import blackjack.domain.Dealer
import blackjack.domain.Name
import blackjack.domain.Players
import blackjack.domain.state.GameResultState

@JvmInline
value class PlayersResult(val playersResultMap: Map<Name, GameResultState>) {

    companion object {

        fun makePlayersResult(dealer: Dealer, players: Players): PlayersResult {
            return players
                .players
                .associate { it.name to dealer.match(it) }
                .let { from(it) }
        }

        fun from(resultMap: Map<Name, GameResultState>): PlayersResult {
            return PlayersResult(resultMap.toMap())
        }
    }
}
