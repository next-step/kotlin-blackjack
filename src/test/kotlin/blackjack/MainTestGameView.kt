package blackjack

import blackjack.business.participants.GameResult
import blackjack.business.participants.Player
import blackjack.business.participants.PlayerResult
import blackjack.business.util.Money
import blackjack.view.GameView

class MainTestGameView : GameView {
    override fun displayGameStartAnnouncement(playerNames: List<String>) {
        println()
        println("${playerNames.joinToString(", ")}에게 2장의 카드를 나누었습니다.")
    }

    override fun displayPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }

    override fun displayPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")} - 결과: ${player.score}")
    }

    override fun displayGameResult(gameResult: GameResult) {
        println()
        println("## 최종 수익")
        printPlayerResult(gameResult.dealerResult)
        gameResult.playerResults.forEach(::printPlayerResult)
    }

    override fun displayDealerDrawCardAnnouncement() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override fun printNewLine() {
        println()
    }

    override fun askForOneMore(playerName: String): String {
        println("$playerName 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        println("y")
        return "y"
    }

    override fun askForPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        println("pobi, jason")
        return listOf("pobi", "jason")
    }

    override fun askForBettingMoney(playerName: String): Money {
        println("$playerName 의 배팅 금액은?")
        println(10000)
        return Money(10000)
    }

    private fun printPlayerResult(playerResult: PlayerResult) {
        println("${playerResult.name}: ${playerResult.profitOrLoss}")
    }
}
