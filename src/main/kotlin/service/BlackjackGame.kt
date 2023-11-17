package service

import domain.BlackjackRules
import domain.Dealer
import domain.Deck
import domain.Player
import view.InputView
import view.OutputView

class BlackjackGame(private val inputView: InputView, private val outputView: OutputView) {
    private val deck = Deck()
    private val players = mutableListOf<Player>()
    private val dealer = Dealer()

    fun addPlayer(name: String) {
        players.add(Player(name))
    }

    fun startGame() {
        dealInitialCards()
        outputView.showInitialCards(players, dealer)

        playPlayersTurn()
        playDealerTurn()

        outputView.showGameResult(players, dealer)
    }

    private fun dealInitialCards() {
        players.forEach {
            drawCardForPlayer(it)
            drawCardForPlayer(it)
        }
        drawCardForDealer()
        drawCardForDealer()
    }

    private fun playPlayersTurn() {
        players.forEach { player ->
            while (player.calculateScore() <= BlackjackRules.MAXIMUM_SCORE && playerWantsToHit(player)) {
                drawCardForPlayer(player)
                outputView.showGameState(players, dealer)
            }
        }
    }

    private fun playDealerTurn() {
        while (dealer.calculateScore() < BlackjackRules.DEALER_HIT_THRESHOLD) {
            drawCardForDealer()
            outputView.showGameState(players, dealer)
        }
    }

    private fun drawCardForPlayer(player: Player) {
        deck.drawCard()?.let { player.receiveCard(it) }
    }

    private fun drawCardForDealer() {
        deck.drawCard()?.let { dealer.receiveCard(it) }
    }

    private fun playerWantsToHit(player: Player): Boolean {
        return inputView.askForAnotherCard(player.name)
    }
}
