package blackjack.app

import blackjack.entity.Dealer
import blackjack.entity.Deck
import blackjack.entity.GameResult
import blackjack.entity.Participants
import blackjack.entity.Player
import blackjack.entity.PlayerAction
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame {
    private val inputView = InputView()
    private val outputView = OutputView()
    private lateinit var deck: Deck

    fun getPlayers(): Participants {
        inputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val input = inputView.readInput()
        println()

        val dealer = Dealer()
        val players =
            input.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { name -> Player(name) }
        return Participants(dealer, players)
    }

    fun gameStart(participants: Participants) {
        deck = Deck()
        participants.initializeHands(deck)
        outputView.printInitialHands(participants)
    }

    fun playTurn(participants: Participants) {
        participants.players.forEach(::playPlayerTurn)
        handleDealerAction(participants)
    }

    fun finishGame(participants: Participants) {
        calculateScore(participants)
        val gameResults = calculateResult(participants)

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

    private fun handleDealerAction(participants: Participants) {
        if (participants.playDealerTurn(deck)) {
            outputView.printDealerDrawCard(participants.dealer)
        }
    }

    private fun calculateScore(participants: Participants) {
        val dealer = participants.dealer
        val dealerScore = dealer.calculateScore()
        outputView.printPlayerResults(dealer.name, dealer.hand, dealerScore)

        participants.players.map { player ->
            outputView.printPlayerResults(player.name, player.hand, player.calculateScore())
        }
    }

    private fun calculateResult(participants: Participants): List<GameResult> {
        return participants.calculateResult()
    }
}
