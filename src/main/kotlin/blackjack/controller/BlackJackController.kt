package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.ui.PlayerInputView
import blackjack.ui.ResultView

object BlackJackController {
    fun run() {
        var players = PlayerInputView.askPlayerNames()
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveTwoCardsToAllPlayers()
        ResultView.printCardsOf(blackjackGame.playerDtos, blackjackGame.dealerDto)

        players.forEach {
            blackjackGame.askAndGiveCards(it)
        }

        blackjackGame.giveCardsToDealer()
        ResultView.printInfoOfDealerBehavior(blackjackGame.dealerDto.cards.size - 2)

        ResultView.printCardResults(blackjackGame.dealerCardResults, blackjackGame.playerCardResults)

        val playerWinTypes = blackjackGame.findPlayerWinTypes()
        ResultView.printWinningResult(playerWinTypes)
    }

    private fun BlackjackGame.askAndGiveCards(player: Player) {
        do {
            val hasAccepted = PlayerInputView.askMoreCard(player.name)
            giveCard(player, hasAccepted)
            ResultView.printCardsOfSinglePlayer(player.toPlayerDto())
        } while (hasAccepted)
    }
}
