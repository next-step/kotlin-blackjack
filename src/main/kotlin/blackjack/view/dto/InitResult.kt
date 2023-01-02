package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Player

data class InitResult(
    val name: String,
    val cards: Set<Card>,
) {
    constructor(player: Player) : this(
        player.name,
        player.hands.cards,
    )

    constructor(dealer: Dealer, firstCard: Set<Card>) : this(dealer.name, firstCard)
}
