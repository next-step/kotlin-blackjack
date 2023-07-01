package blackjack.dto

import blackjack.Card
import blackjack.CardScoreCalculator
import blackjack.Player

data class PlayerGameResult(
    val name: String,
    val cards: List<Card>,
    val score: Int,
) {
    companion object {
        fun from(player: Player): PlayerGameResult {
            return PlayerGameResult(
                name = player.name,
                cards = player.currentCards,
                score = CardScoreCalculator.calculate(player.currentCards)
            )
        }
    }
}
