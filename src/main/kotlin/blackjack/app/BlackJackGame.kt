package blackjack.app

import blackjack.entity.Deck
import blackjack.entity.Player
import blackjack.entity.PlayerResult
import blackjack.entity.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame {
    private val inputView = InputView()
    private val outputView = OutputView()
    private lateinit var deck: Deck

    fun getPlayers(): Players {
        inputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val input = inputView.readInput()
        println()

        val players =
            input.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { name -> Player(name) }
        return Players(players)
    }

    fun gameStart(players: Players) {
        deck = Deck()
        players.initializeHands(deck)
        outputView.printInitialHands(players)
    }

    fun playTurns(players: Players) {
        players.participants.forEach { player ->
            var wantsToHit = true
            while (wantsToHit) {
                wantsToHit = inputView.askForHitOrStand(player.name)
                if (wantsToHit) {
                    player.receiveCard(deck.deal())
                    outputView.printPlayerHand(player)
                }
            }
        }
    }

    fun finishGame(players: Players) {
        val playerResults =
            calculateResults(players)
        outputView.printPlayerResults(playerResults)
    }

    private fun calculateResults(players: Players): List<PlayerResult> {
        val playerResults =
            players.participants.map { player ->
                val score = player.calculateScore()
                PlayerResult(player.name, player.hand, score)
            }
        return playerResults
    }
}
