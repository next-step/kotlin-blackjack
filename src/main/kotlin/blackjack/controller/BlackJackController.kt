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

        val playerDTOs = dealer.giveTwoCardsToAllPlayers()
        ResultView.printCardsOf(playerDTOs)

        players.filter { it != dealer }.forEach {
            askAndGiveCards(it, dealer)
        }

        val wasUnderSixteen = dealer.takeCardIfUnderSixteen()
        if (wasUnderSixteen) {
            ResultView.printInfoOfThirdCardOfDealer()
        }

        val playerCardResults = players.toPlayerCardResults()
        ResultView.printCardResults(playerCardResults)

        val playerWinTypes = dealer.findPlayerWinTypes()
        ResultView.printWinningResult(playerWinTypes)
    }

    private fun askAndGiveCards(player: Player, dealer: Dealer) {
        do {
            val hasAccepted = PlayerInputView.askMoreCard(player.name)
            val playerDto = dealer.giveCard(player, hasAccepted)
            ResultView.printCardsOfSinglePlayer(playerDto)
        } while (hasAccepted)
        println()
    }
}
