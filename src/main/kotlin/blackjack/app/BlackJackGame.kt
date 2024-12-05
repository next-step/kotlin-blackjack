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

    fun playTurns(participants: Participants) {
        participants.players.forEach { player ->
            var wantsToHit: Boolean
            do {
                wantsToHit = inputView.askForHitOrStand(player.name)
                val action = player.playTurn(deck, wantsToHit)
                when (action) {
                    PlayerAction.HIT -> outputView.printPlayerHand(player)
                    PlayerAction.BURST -> {
                        outputView.printPlayerBusted(player)
                        break
                    }

                    PlayerAction.BLACKJACK -> {
                        outputView.printPlayerBlackjack(player)
                        break
                    }

                    PlayerAction.STAND -> break
                    PlayerAction.DRAW -> continue
                }
            } while (action == PlayerAction.HIT)
        }
    }

    fun playDealerTurn(participants: Participants) {
        if (participants.playDealerTurn(deck)) {
            outputView.printDealerDrawCard(participants.dealer)
        }
    }

    fun finishGame(participants: Participants) {
        calculateScore(participants)
        val gameResults = calculateResult(participants)

        outputView.printGameResult(gameResults)
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
