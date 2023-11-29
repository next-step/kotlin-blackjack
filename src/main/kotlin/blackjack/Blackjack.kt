package blackjack

import blackjack.domain.BlackjackResult
import blackjack.domain.PlayerResult
import blackjack.domain.cards.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState
import blackjack.view.InputView
import blackjack.view.InputViewCommand
import blackjack.view.ResultView
import blackjack.view.UserInputView

class Blackjack(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    private val dealer = Dealer(Deck.fullDeck())

    fun simulate() {
        val playerNames = inputView.getPlayerNames()

        dealer.initHand()

        val players = createPlayers(playerNames)

        resultView.printInitialState(players)

        players.forEach { player ->
            processPlayerTurn(player)
        }

        dealer.processTurn {
            resultView.printDealerTurn(it)
        }

        resultView.printPlayer(dealer.asPlayer)
        resultView.printResult(players)

        val gameResult = BlackjackResult(
            players.map { player ->
                PlayerResult(player, dealer.wins(player))
            }
        )

        resultView.printBlackjackResult(gameResult)
    }

    private fun processPlayerTurn(player: Player) {
        while (player.state == PlayerState.Hit) {
            val command = inputView.getPlayerCommand(player.name)
            player.play(command == InputViewCommand.Yes)
            resultView.printPlayer(player)
        }
    }

    private fun createPlayers(playerNames: List<String>): List<Player> {
        return playerNames.map { Player(it, dealer.createInitialHand()) }
    }

    private fun Player.play(isHit: Boolean) {
        if (isHit) {
            hit()
            val card = dealer.provideCard()
            addCard(card)
        } else {
            stay()
        }
    }
}

fun main() {
    Blackjack(
        UserInputView(),
        ResultView(),
    ).simulate()
}
