package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.game.GameResult

class DealEnd(
    override val table: GameTable
) : CardDistributor() {

    override fun deal(): GameResult =
        GameResult.of(table.players, table.dealer)

    override fun nextDistributor(): CardDistributor {
        throw IllegalArgumentException("마지막 카드 배분입니다")
    }
}
