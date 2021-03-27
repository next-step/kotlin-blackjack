package blackjack.controller

import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.ui.PlayerInputView
import blackjack.ui.ResultView

object BlackJackController {
    fun run() {
        val cardPack = CardPack()
        val players = PlayerInputView.askPlayerNames()
        val dealer = Dealer(players, cardPack)

        dealer.giveTwoCardsToAllPlayers()
        ResultView.printCardsOf(players.toPlayerDtos(), dealer.toPlayerDto())

        players.forEach {
            askAndGiveCards(it, dealer)
        }

        while (dealer.isUnderSixteen) {
            dealer.takeCard()
            ResultView.printInfoOfDealerBehavior()
        }

        val playerCardResults = players.toPlayerCardResults()
        ResultView.printCardResults(dealer.cardResult, playerCardResults)

        val playerWinTypes = dealer.findPlayerWinTypes()
        ResultView.printWinningResult(playerWinTypes)
    }

    private fun askAndGiveCards(player: Player, dealer: Dealer) {
        do {
            val hasAccepted = PlayerInputView.askMoreCard(player.name)
            dealer.giveCard(player, hasAccepted)
            ResultView.printCardsOfSinglePlayer(player.toPlayerDto())
        } while (hasAccepted)
    }
}
