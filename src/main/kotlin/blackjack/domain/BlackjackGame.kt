package blackjack.domain

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
        } else {
            player.stay()
        }
    }

    fun giveCardsToDealer() {
        while (dealer.isUnderSixteen) {
            dealer.takeCard(cardPack.poll())
        }
        dealer.stay()
    }

    fun findProfits(): Profits {
        val playerProfits = players.map {
            it.profit(dealer.state)
        }

        val dealerProfitAmount = dealer.profit(playerProfits)
        val dealerProfit = Profit(dealer.name, dealerProfitAmount)

        return Profits(dealerProfit, playerProfits)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
