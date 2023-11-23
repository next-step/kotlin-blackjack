package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.GameTable
import blackjack.domain.result.DealInitialCardResult

class DistributionEnd : CardDistributor {

    override fun invoke(game: BlackJackGame, table: GameTable): DealInitialCardResult {
        throw IllegalArgumentException("더 이상 카드를 배분할 수 없습니다")
    }
}
