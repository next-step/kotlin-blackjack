package blackjack.view

import blackjack.domain.Player
import blackjack.view.OutputView.display

class BlackJackGameResult(
    private val players: List<Player>,
) {
    fun gameResult(): List<Map<String, Any>> {
        return players.map {
            mapOf(
                "name" to it.name,
                "cards" to it.playerCard.cards.joinToString(", ") { card -> card.display },
                "score" to it.playerCard.score().score,
            )
        }
    }
}
