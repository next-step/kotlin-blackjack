package blackjack.app

import blackjack.entity.Deck
import blackjack.entity.Player
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
}
