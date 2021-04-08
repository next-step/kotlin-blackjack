package blackjack.domain

import java.math.BigDecimal

class BlackjackGame(val players: Players, val dealer: Dealer = Dealer()) {

    private val cardPack = CardPack()

    val addedCardNumberOfDealer: Int
        get() = dealer.cardSize - FIRST_GIVEN_CARD_SIZE

    fun giveTwoCardsToAllPlayers() {
        players.giveFirstTwoCardsToAllPlayers(cardPack)
        dealer.takeFirstTwoCards(cardPack.poll(), cardPack.poll())
    }

    fun giveCard(player: Player, hasAccepted: Boolean) {
        if (hasAccepted) {
            player.takeCard(cardPack.poll())
        }
    }

    fun giveCardsToDealer() {
        while (dealer.isUnderSixteen) {
            dealer.takeCard(cardPack.poll())
        }
    }

    fun findProfits(): Profits {
        val dealerPoint = PlayerPoint(dealer.cardPointSum(), dealer.isBlackjack)

        val playerProfits = players.map {
            it.profit(dealerPoint)
        }

        val dealerProfitAmount =
            playerProfits.fold(BigDecimal.ZERO) { acc, profit -> acc + profit.amount } * BigDecimal("-1")
        val dealerProfit = Profit(dealer.name, dealerProfitAmount)

        return Profits(dealerProfit, playerProfits)
    }

    private fun Player.profit(dealerPoint: PlayerPoint): Profit {
        val playerPoint = PlayerPoint(this.cardPointSum(), this.isBlackjack)
        val playerWinType = PlayerWinType.findPlayerWinType(playerPoint, dealerPoint)
        val profitAmount = playerWinType.calculateProfit(this.price)
        return Profit(this.name, profitAmount)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
