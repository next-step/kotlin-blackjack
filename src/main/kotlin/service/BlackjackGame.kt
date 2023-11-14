package service

import domain.Dealer
import domain.Deck
import domain.Player
import dto.GameResultDTO
import dto.GameStateDTO
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
        outputView.showGameState(createGameStateDTO())

        playPlayersTurn()
        playDealerTurn()

        val gameResult = createGameResultDTO()
        outputView.showGameResult(gameResult)
    }

    private fun dealInitialCards() {
        players.forEach {
            drawCardForPlayer(it)
            drawCardForPlayer(it)
        }
        drawCardForDealer()
        drawCardForDealer()

        outputView.showInitialCards(createGameStateDTO())
    }

    private fun playPlayersTurn() {
        players.forEach { player ->
            while (player.calculateScore() <= BlackjackRules.MAXIMUM_SCORE && playerWantsToHit(player)) {
                drawCardForPlayer(player)
                outputView.showGameState(createGameStateDTO())
            }
        }
    }

    private fun playDealerTurn() {
        while (dealer.calculateScore() < BlackjackRules.DEALER_HIT_THRESHOLD) {
            drawCardForDealer()
            outputView.showGameState(createGameStateDTO())
        }
    }

    private fun drawCardForPlayer(player: Player) {
        deck.drawCard()?.let { player.receiveCard(it) }
    }

    private fun drawCardForDealer() {
        deck.drawCard()?.let { dealer.receiveCard(it) }
    }

    private fun createGameStateDTO(): GameStateDTO {
        return GameStateDTO(
            playerHands = players.associate { it.name to it.showHand() },
            dealerHand = dealer.showHand()
        )
    }

    private fun createGameResultDTO(): GameResultDTO {
        return GameResultDTO(
            finalScores = players.associate { player ->
                val playerScore = player.calculateScore()
                val playerHand = player.showHand()
                player.name to Pair(playerHand, playerScore.toString())
            }
        )
    }

    private fun playerWantsToHit(player: Player): Boolean {
        return inputView.askForAnotherCard(player.name)
    }
}
