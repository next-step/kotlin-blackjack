package blackjack.domain.game

import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.score.CardScoreCalculator

data class PlayerGameResult(
    val playerCardDeck: PlayerCardDeckCapture,
) {

    val score = CardScoreCalculator.calculateScore(playerCardDeck.cards).value
}
