package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.holder.Player

data class AddCardResult(
    val name: String,
    val cards: Set<Card>,
    val bust: Boolean,
) {
    constructor(player: Player) : this(
        player.name,
        player.hands.cards,
        player.bust()
    )
}
