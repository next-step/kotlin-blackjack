package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.Result

interface CardDistributor {
    operator fun invoke(table: GameTable, decideDistributor: (distributor: CardDistributor) -> Unit): Result
}
