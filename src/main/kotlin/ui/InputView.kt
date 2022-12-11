package ui

import model.BlackJackGame
import model.Dealer
import model.Player
import model.Players
import ui.ResultView.resultPlayerCard

object InputView {

    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames: String = readLine() ?: throw IllegalArgumentException("이름을 입력해야 합니다")

        return Players(playerNames)
    }

    fun inputPlayerQuestion(blackJackGame: BlackJackGame) {
        blackJackGame.getPlayers().forEach {
            inputQuestionHit(it, blackJackGame.dealer)
        }
        inputDealerHit(blackJackGame.dealer)
    }

    private fun inputDealerHit(dealer: Dealer) {
        dealer.hitIfRequired()

        if (dealer.cards.count() == 3) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }
    }

    private fun inputQuestionHit(player: Player, dealer: Dealer) {
        println()
        var process = "y"
        while (process == "y") {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val answer: String = readLine() ?: throw IllegalArgumentException("예는 y, 아니오는 n 을 입력해야 합니다")
            require(answer in listOf("y", "n")) { "예는 y, 아니오는 n 을 입력해야 합니다" }
            playerHit(answer, player, dealer)
            process = answer
        }
    }

    private fun playerHit(answer: String, player: Player, dealer: Dealer) {
        if (answer == "y") {
            player.hit(dealer.pick())
            resultPlayerCard(player)
        }
    }
}
