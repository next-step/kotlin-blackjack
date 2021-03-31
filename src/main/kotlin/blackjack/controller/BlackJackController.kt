package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Player
import blackjack.ui.PlayerInputView
import blackjack.ui.ResultView
import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto

object BlackJackController {
    fun run() {
        val players = PlayerInputView.askPlayerNames()
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveTwoCardsToAllPlayers()

        val playerDtos = blackjackGame.players.map { PlayerDto(it) }
        val dealerDto = PlayerDto(blackjackGame.dealer)
        ResultView.printCardsOf(playerDtos, dealerDto)

        players.forEach {
            blackjackGame.askAndGiveCards(it)
        }

        blackjackGame.giveCardsToDealer()
        ResultView.printInfoOfDealerBehavior(blackjackGame.addedCardNumberOfDealer)

        val dealerResult = PlayerCardResult(blackjackGame.dealer)
        val playerResults = blackjackGame.players.map { PlayerCardResult(it) }
        ResultView.printCardResults(dealerResult, playerResults)

        val playerWinTypes = blackjackGame.findPlayerWinTypes()
        ResultView.printWinningResult(playerWinTypes)
    }

    private fun BlackjackGame.askAndGiveCards(player: Player) {
        do {
            val hasAccepted = PlayerInputView.askMoreCard(player.name)
            giveCard(player, hasAccepted)
            ResultView.printCardsOfSinglePlayer(PlayerDto(player))
        } while (hasAccepted)
    }
}
