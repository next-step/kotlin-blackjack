package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.Dealer
import blackjack.view.BlackjackView
import blackjack.view.input.BlackjackInputReader

class BlackjackController(
    private val blackjackView: BlackjackView,
    private val blackjackInputReader: BlackjackInputReader,
    private val cardVendor: CardVendor = DefaultCardVendor()
) {
    fun run() {
        val playerNames = blackjackInputReader.readPlayerNames()
        val dealerName = Dealer.DEFAULT_DEALER_NAME

        val playerBettingAmounts = blackjackInputReader.readPlayerBettingAmounts(playerNames)

        val blackjack = Blackjack.of(dealerName, playerNames, playerBettingAmounts, cardVendor)
        val dealer = blackjack.dealer
        val players = blackjack.players

        blackjackView.printlnInitialPlayersCards(dealer, players)

        blackjackView.printlnBlackjack(blackjack)

        blackjackInputReader.readPlayersHitOrStay(blackjack, blackjack::hitOrStay)

        blackjack.complete()

        if (dealer.isAddedCards()) {
            blackjackView.printlnDealerAddedCards(dealer)
        }

        blackjackView.printlnBlackjackResult(blackjack)

        blackjackView.printlnBlackjackFinalProfit(blackjack)
    }
}
