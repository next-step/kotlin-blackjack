package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Player

data class GameResult(
    val playerName: String,
    val cards: List<Card>,
    val score: Int,
) {
    companion object {
        fun from(player: Player): GameResult =
            GameResult(playerName = player.name.value, cards = player.cards(), score = player.score())
    }
}
