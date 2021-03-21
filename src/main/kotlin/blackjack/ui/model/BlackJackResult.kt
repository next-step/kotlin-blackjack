package blackjack.ui.model

import blackjack.domain.Player

class BlackJackResult(
    val name: String,
    val cardNames: List<String>,
    val point: Int
) {
    companion object {
        fun from(player: Player): BlackJackResult {
            return BlackJackResult(player.name, player.cardNames, player.calculateCardSum())
        }
    }
}
