package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.GameTable
import blackjack.domain.result.Result

interface CardDistributor {
    operator fun invoke(game: BlackJackGame, table: GameTable): Result
}
