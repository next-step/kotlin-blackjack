package blackjack.domain.state

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Cards

class Stay(
    cards: Cards
) : Finished(cards) {

    override fun calculateResult(otherScore: Score): GameResult.Result {
        // VO인 Score에게 로직을 위임한다면 좀 더 간결해지겠지만... VO가 블랙잭에 관한 값을 아는 게 이상해서 이런 코드가 나왔습니다 ㅠㅠ
        // 분기가 너무 많은데 어떤 식으로 개선할 수 있을까요?
        val score = cards().calculateScore()
        if (otherScore == Cards.BLACKJACK_SCORE) return GameResult.Result.LOSE
        if (otherScore > Cards.BLACKJACK_SCORE) return GameResult.Result.WIN
        if (score == otherScore) return GameResult.Result.DRAW
        if (score < otherScore) return GameResult.Result.LOSE
        return GameResult.Result.WIN
    }
}
