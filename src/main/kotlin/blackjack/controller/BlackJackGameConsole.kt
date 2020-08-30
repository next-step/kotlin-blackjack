package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.profit.Profits
import blackjack.view.InputView
import blackjack.view.POSSIBLE_REPLIES
import blackjack.view.ResultView

class BlackJackGameConsole(private val game: BlackJackGame) {

    fun playGame() {
        val dealer: Dealer = game.setUpDealer()
        val players: Players = game.setUpPlayers()
        ResultView.showSetUpResult(dealer, players)

        val playersFinished: Players = playByPlayers(players)
        val dealerFinished: Dealer = game.playByDealer(dealer) { showMessageOfGettingCard(dealer) }
        ResultView.showScoreResult(dealerFinished, playersFinished)

        val profits: Profits = game.getProfits(dealerFinished, playersFinished)
        ResultView.showProfitResult(profits)
    }

    private fun playByPlayers(players: Players): Players {
        val playersInProgress: MutableList<Player> = mutableListOf()

        while (!game.isTurnOver()) {
            val currentPlayer: Player = players.players[game.turn]
            val playerFinished = game.playByPlayer(
                currentPlayer, { hasHitMessageFrom(it) }, { showCardsOf(it) }
            )

            playersInProgress.add(playerFinished)
        }
        return Players(playersInProgress)
    }

    private fun hasHitMessageFrom(player: Player): Boolean {
        val playerReply: String = InputView.readReplyToHit(player)
        val hitMessage: String = POSSIBLE_REPLIES[0]
        return playerReply == hitMessage
    }

    private fun showCardsOf(player: Player) {
        ResultView.showCardsOf(player)
    }

    private fun showMessageOfGettingCard(dealer: Dealer) {
        ResultView.showMessageOfGettingCard(dealer)
    }
}
