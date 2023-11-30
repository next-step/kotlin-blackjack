package blackjack

import blackjack.card.CardDeck
import blackjack.participant.AbstractPlayer
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.ui.InputManager
import blackjack.ui.OutputManager

class GameManager(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    private val players: List<AbstractPlayer>
    private val dealer: Dealer = Dealer(ScoreCalculator())

    init {
        players = joinPlayers()
    }

    fun start() {
        players.forEach { it.drawCard(CardDeck.draw(FIRST_DRAW)) }
        dealer.drawCard(CardDeck.draw(FIRST_DRAW))

        outputManager.printFirstTurn(players)
        outputManager.printPlayersCards(players)

        playBlackJack()
        dealersTurn()

        players.forEach {
            outputManager.printPlayerResultGame(it)
        }
    }

    private fun playBlackJack() {
        players.filter { !it.isDealer() }.forEach {
            playerDraw(it)
        }
    }

    private fun dealersTurn() {
        players.filter { it.isDealer() }.forEach {
            playerDraw(it)
        }
    }
    private fun playerDraw(player: AbstractPlayer) {
        var drawAmount = -1

        while (player.shouldDraw() && drawAmount != 0) {
            drawAmount = inputManager.inputShouldDrawCard(player)
            if (playerChooseDraw(drawAmount)) {
                player.drawCard(CardDeck.draw(drawAmount))
            }
            outputManager.printPlayerCards(player)
        }
    }

    private fun playerChooseDraw(drawAmount: Int) = drawAmount > 0

    private fun joinPlayers(): List<AbstractPlayer> {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        return listOf(Dealer(ScoreCalculator())) + playerNames.map { Player(it, ScoreCalculator()) }
    }

    companion object {
        private const val FIRST_DRAW: Int = 2
    }
}

fun main() {
    GameManager(InputManager(), OutputManager()).start()
}
