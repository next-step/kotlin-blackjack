package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Suit

class BlackJackGameResult(
    private val player: Player,
) {
    val playerName: String get() = player.name
    val cards: String
        get() = player.cardSet.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }
    val score: Int get() = player.cards.score().score

    private fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }
}
