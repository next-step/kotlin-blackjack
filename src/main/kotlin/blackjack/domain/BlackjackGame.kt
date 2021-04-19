package blackjack.domain

import blackjack.domain.card.CardPack

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
        } else if (player.state.isRunning) {
            player.stay()
        }
    }

    fun giveCardsToDealer() {
        while (dealer.isUnderSixteen) {
            dealer.takeCard(cardPack.poll())
        }
        if (dealer.state.isRunning) {
            dealer.stay()
        }
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
