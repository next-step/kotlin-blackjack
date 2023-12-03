package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Cards

sealed class Running(
    private val cards: Cards
): State {
    override fun cards(): Cards {
        return cards
    }

    override fun calculateResult(otherScore: Score): GameResult.Result {
        throw IllegalStateException("게임이 진행 중이기 때문에 결과를 계산할 수 없습니다.")
    }
}
