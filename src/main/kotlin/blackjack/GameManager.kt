package blackjack

import blackjack.card.CardDeck
import blackjack.supoort.GameResultGenerator
import blackjack.supoort.ScoreCalculator
import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.ui.InputManager
import blackjack.ui.OutputManager

class GameManager(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    private val players: List<Player>
    private val dealer: Dealer

    init {
        players = joinPlayers()
        dealer = joinDealer()
    }

    fun start() {
        players.forEach { it.drawCard(CardDeck.draw(FIRST_DRAW)) }
        dealer.drawCard(CardDeck.draw(FIRST_DRAW))

        outputManager.printFirstTurn(players)
        outputManager.printPlayersAndDealerCards(players, dealer)

        playBlackJack()

        val result = GameResultGenerator.generateGameResult(players, dealer)

        outputManager.printDealerResultGame(dealer)

        players.forEach(outputManager::printPlayerResultGame)
        outputManager.printResult(result)
    }

    private fun playBlackJack() {
        players.forEach {
            playerDraw(it)
        }
        dealerDraw(dealer)
    }

    private fun playerDraw(player: Player) {
        var drawAmount = -1

        while (player.shouldDraw() && drawAmount != 0) {
            drawAmount = inputManager.inputShouldDrawCard(player.name.value)
            if (playerChooseDraw(drawAmount)) {
                player.drawCard(CardDeck.draw(drawAmount))
            }
            outputManager.printPlayerCards(player)
        }
    }

    private fun dealerDraw(dealer: Dealer) {
        while (dealer.shouldDraw()) {
            outputManager.printDealerCanDrawMessage()
            dealer.drawCard(CardDeck.draw(DRAW_CARD))
        }
    }

    private fun playerChooseDraw(drawAmount: Int) = drawAmount > 0

    private fun joinPlayers(): List<Player> {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        return playerNames.map { Player(Name(it), BettingAmount(inputManager.inputBettingAmount(it))) }
    }

    private fun joinDealer(): Dealer {
        return Dealer(ScoreCalculator())
    }

    companion object {
        private const val FIRST_DRAW: Int = 2
        private const val DRAW_CARD: Int = 1
    }
}

fun main() {
    GameManager(InputManager(), OutputManager()).start()
}
