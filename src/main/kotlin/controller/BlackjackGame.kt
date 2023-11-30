package controller

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

    fun startGame() {
        initializeGame()
        conductGame()
        concludeGame()
    }

    private fun initializeGame() {
        val playerNames = inputView.readPlayerNames()
        playerNames.forEach {
            val bettingAmount = inputView.readBettingAmount(it)
            val player = Player(it, bettingAmount)
            players.add(player)
        }
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
        val dealerProfit = dealer.calculateProfit(players)
        outputView.showFinalResults(players, dealer, dealerProfit)
    }

    private fun determineResults() {
        val dealerScore = dealer.calculateScore()
        val dealerCards = dealer.showHand()

        players.forEach { player ->
            player.determineResult(dealerScore, dealerCards)
        }
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
            val card = deck.drawCard()
            if (card != null) {
                dealer.receiveCard(card)
                outputView.showGameState(players, dealer)
            }
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
