package blackjack.domain.game

import blackjack.domain.gamer.PlayerCards
import blackjack.domain.score.CardScoreCalculator

data class PlayerGameResult(
    val playerCards: PlayerCards,
) {

    val score = CardScoreCalculator.calculateScore(playerCards.cards)
}
