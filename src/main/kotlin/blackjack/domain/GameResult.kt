package blackjack.domain

import blackjack.domain.card.Card

data class GameResult(
    val playerName: String,
    val cards: List<Card>,
    val score: Int,
) {
    companion object {
        fun from(player: Player): GameResult =
            GameResult(playerName = player.name, cards = player.cards(), score = player.score())
    }
}
