package blackjack.app

import blackjack.entity.BettingAmount
import blackjack.entity.Dealer
import blackjack.entity.Deck
import blackjack.entity.GameResult
import blackjack.entity.Player
import blackjack.entity.PlayerAction
import blackjack.entity.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame {
    private val inputView = InputView()
    private val outputView = OutputView()
    private lateinit var deck: Deck
    private val dealer = Dealer()

    fun getPlayers(): Players {
        inputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = inputView.readInput()
        println()

        val playerName =
            input.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
        val players =
            playerName.map {
                val bet = inputView.askForBet(it)
                println()
                Player(it, BettingAmount(bet))
            }

        return Players(players)
    }

    fun gameStart(players: Players) {
        deck = Deck()
        dealer.initializeHand(deck)
        players.initializeHands(deck)
        outputView.printInitialHands(players, dealer)
    }

    fun playTurn(players: Players) {
        players.forEach(::playPlayerTurn)
        handleDealerAction()
    }

    fun finishGame(players: Players) {
        calculateScore(players)
        val gameResults = calculateResult(players)

        outputView.printGameResult(gameResults)
    }

    private fun playPlayerTurn(player: Player) {
        generateSequence {
            val wantsToHit = inputView.askForHitOrStand(player.name)
            val action = player.playTurn(deck, wantsToHit)
            handlePlayerAction(player, action)
        }.toList()
    }

    private fun handlePlayerAction(
        player: Player,
        action: PlayerAction,
    ): PlayerAction? {
        return when (action) {
            PlayerAction.HIT -> {
                outputView.printPlayerHand(player)
                action
            }

            PlayerAction.BURST -> {
                outputView.printPlayerBusted(player)
                null
            }

            PlayerAction.BLACKJACK -> {
                outputView.printPlayerBlackjack(player)
                null
            }

            PlayerAction.STAND -> null
            PlayerAction.DRAW -> action
        }
    }

    private fun handleDealerAction() {
        if (dealer.playTurn(deck) == PlayerAction.DRAW) {
            outputView.printDealerDrawCard(dealer)
        }
    }

    private fun calculateScore(players: Players) {
        val dealerScore = dealer.calculateScore()
        outputView.printPlayerResults(dealer.name, dealer.hand, dealerScore)

        players.players.map { player ->
            outputView.printPlayerResults(player.name, player.hand, player.calculateScore())
        }
    }

    private fun calculateResult(players: Players): List<GameResult> {
        val playerResults = players.calculateResult(dealer)
        val dealerResult = dealer.calculateResult(players)
        return listOf(dealerResult) + playerResults
    }
}
