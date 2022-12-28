package blackjack.view.dto

import blackjack.domain.*

data class PlayerGameResult(
    val name: String,
    val cards: Set<Card>,
    val totalPoint: Point,
    val winningAmount: Int = 0,
) {
    constructor(player: Player, winningAmount: Int) : this(
        player.name,
        player.playingCards.cards,
        player.playingCards.calculatePoint(),
        winningAmount,
    )

    constructor(dealer: Dealer, dealerBettingAmount: Int) : this(
        dealer.name,
        dealer.playingCards.cards,
        dealer.playingCards.calculatePoint(),
        dealerBettingAmount,
    )
}
