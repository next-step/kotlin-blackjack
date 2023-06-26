package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.score.CardScoreCalculator

data class DelayerGameResult(
    val dealerCards: List<Card>,
) {
    val score = CardScoreCalculator.calculateScore(dealerCards)
}
