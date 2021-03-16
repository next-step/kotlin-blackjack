package blackjack.controller

import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.ui.CardView
import blackjack.ui.PlayerInputView

object BlackJackController {
    fun run() {
        val cardPack = CardPack()
        val players = PlayerInputView.askPlayerNames()
        val dealer = Dealer(players, cardPack)

        val playerDTOs = dealer.giveTwoCardsToAllPlayers()
        CardView.printCardsOf(playerDTOs)

        val blackJackResults = dealer.giveCardUntilStop(
            PlayerInputView::askMoreCard,
            CardView::printCardsOfSinglePlayer
        )

        CardView.printResults(blackJackResults)
    }
}
