package controller

import domain.BlackjackRules
import domain.Dealer
import domain.Deck
import domain.Player
import enum.GameResult
import view.InputView
import view.OutputView

class BlackjackGame(private val inputView: InputView, private val outputView: OutputView) {
    private val deck = Deck()
    private val players = mutableListOf<Player>()
    private val dealer = Dealer()

    fun startGame() {
        initializeGame()
        conductGame()
        concludeGame()
    }

    private fun initializeGame() {
        val playerNames = inputView.readPlayerNames()
        playerNames.forEach { addPlayer(it) }
        dealInitialCards()
        outputView.showInitialCards(players, dealer)
    }

    private fun conductGame() {
        playPlayersTurn()
        playDealerTurn()
    }

    private fun concludeGame() {
        determineResults()
        outputView.showPlayerResults(players, dealer)
        outputView.showFinalResults(players, dealer)
    }

    private fun determineResults() {
        val dealerScore = dealer.calculateScore()
        players.forEach { player ->
            val playerScore = player.calculateScore()
            player.result = GameResult.determine(playerScore, dealerScore, BlackjackRules.MAXIMUM_SCORE)
        }
    }

    private fun addPlayer(name: String) {
        players.add(Player(name))
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
