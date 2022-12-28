package blackjack.view.dto

import blackjack.domain.*

data class InitResult(
    val name: String,
    val cards: Set<Card>,
) {
    constructor(player: Player) : this(
        player.name,
        player.playingCards.cards,
    )

    constructor(dealer: Dealer, firstCard: Set<Card>) : this(dealer.name, firstCard)
}
