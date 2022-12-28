package blackjack.view.dto

import blackjack.domain.*

data class AddCardResult(
    val name: String,
    val cards: Set<Card>,
    val bust: Boolean,
) {
    constructor(player: Player) : this(
        player.name,
        player.playingCards.cards,
        player.bust()
    )
}
