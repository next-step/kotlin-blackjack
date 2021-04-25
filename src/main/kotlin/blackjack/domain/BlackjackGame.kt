package blackjack.domain

import blackjack.domain.card.CardPack
import java.math.BigDecimal

class BlackjackGame(val players: Players, val dealer: Dealer = Dealer(), private val cardPack: CardPack = CardPack()) {

    val addedCardNumberOfDealer: Int
        get() = dealer.cardSize - FIRST_GIVEN_CARD_SIZE

    fun giveTwoCardsToAllPlayers() {
        players.giveFirstTwoCardsToAllPlayers(cardPack)
        dealer.takeFirstTwoCards(cardPack.poll(), cardPack.poll())
    }

    fun giveCard(player: Player, hasAccepted: Boolean) {
        if (hasAccepted) {
            player.takeCard(cardPack.poll())
        } else if (player.isRunning) {
            player.stay()
        }
    }

    fun giveCardsToDealer() {
        dealer.takeCardUntilSixteen(cardPack)
    }

    fun findProfits(): Profits {
        val playerProfits = players.map {
            it.profit(dealer.state)
        }

        val dealerProfitAmount = sumOfPlayerProfits(playerProfits) * BigDecimal("-1")
        val dealerProfit = Profit(dealer.name, dealerProfitAmount)

        return Profits(dealerProfit, playerProfits)
    }

    private fun sumOfPlayerProfits(playerProfits: List<Profit>): BigDecimal {
        return playerProfits.fold(BigDecimal.ZERO) { acc, profit -> acc + profit.amount }
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
