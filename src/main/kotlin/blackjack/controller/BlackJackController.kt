package blackjack.controller

import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.ui.CardView
import blackjack.ui.PlayerInputView
import blackjack.ui.model.BlackJackResult

object BlackJackController {
    fun run() {
        val cardPack = CardPack()
        val players = PlayerInputView.askPlayerNames()
        val dealer = Dealer(players, cardPack)

        val playerDTOs = dealer.giveTwoCardsToAllPlayers()
        CardView.printCardsOf(playerDTOs)

        players.forEach {
            askAndGiveCards(it, dealer)
        }
        val blackJackResults = players.map { BlackJackResult(it) }

        CardView.printResults(blackJackResults)
    }

    private fun askAndGiveCards(player: Player, dealer: Dealer) {
        while (PlayerInputView.askMoreCard(player.name)) {
            dealer.giveCard(player)
            CardView.printCardsOfSinglePlayer(player.toPlayerDTO())
        }
        CardView.printCardsOfSinglePlayer(player.toPlayerDTO())
    }
}
