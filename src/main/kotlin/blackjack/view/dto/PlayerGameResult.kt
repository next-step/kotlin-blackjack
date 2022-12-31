package blackjack.view.dto

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Player
import blackjack.domain.value.Point

data class PlayerGameResult(
    val name: String,
    val cards: Set<Card>,
    val totalPoint: Point,
    val winningAmount: Int = 0,
) {
    constructor(player: Player, winningAmount: Int) : this(
        player.name,
        player.state.hands.cards,
        player.state.hands.calculatePoint(),
        winningAmount,
    )

    constructor(dealer: Dealer, dealerBettingAmount: Int) : this(
        dealer.name,
        dealer.hands.cards,
        dealer.hands.calculatePoint(),
        dealerBettingAmount,
    )
}
