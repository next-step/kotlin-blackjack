package blackjack.domain

import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.PlayerProfit

interface Player {
    val name: String

    val play: Play

    val bet: Bet

    fun profit(dealer: Dealer): PlayerProfit.Player
}

interface Dealer {
    val deck: CardDeck
    val name: String
    val play: Play
    fun deliverCard(): Card
    fun shuffle()

    fun profit(gamePlayerProfits: List<PlayerProfit.Player>): PlayerProfit.Dealer
}
