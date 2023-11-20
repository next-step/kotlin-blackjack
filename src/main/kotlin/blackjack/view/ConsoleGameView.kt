package blackjack.view

import blackjack.business.participants.Dealer
import blackjack.business.participants.Player
import blackjack.business.participants.PlayerResult
import blackjack.business.participants.Players
import blackjack.business.util.PlayerNameParser

class ConsoleGameView : GameView {
    override fun displayGameStartAnnouncement(playerNames: List<String>) {
        println()
        println("${playerNames.joinToString(", ")}에게 2장의 카드를 나누었습니다.")
    }

    override fun displayDealerCards(dealer: Dealer) {
        println("딜러: ${dealer.cards.joinToString(", ") { it.toString() }}")
    }

    override fun displayPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }

    override fun displayPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")} - 결과: ${player.score}")
    }

    override fun displayDealerResult(dealer: Dealer) {
        println("딜러 카드: ${dealer.cards.joinToString(", ")} - 결과: ${dealer.score}")
    }

    override fun displayGameResult(dealer: Dealer, players: Players) {
        println()
        println("## 최종 승패")
        val dealerResult = dealer.getDealerResult(players)
        val result = dealerResult.map { "${it.value}${it.key.message}" }
        println("딜러: ${result.joinToString(", ")}")
        players.forEachPlayer { printPlayerResult(dealer.getPlayerResult(it)) }
    }

    override fun displayDealerDrawCardAnnouncement() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override fun askForOneMore(playerName: String): String {
        println("$playerName 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readlnOrNull() ?: throw IllegalArgumentException("잘못된 입력입니다.")
    }

    override fun askForPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return PlayerNameParser.parse(
            readlnOrNull() ?: throw IllegalArgumentException("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        )
    }

    private fun printPlayerResult(playerResult: PlayerResult) {
        println("${playerResult.name}: ${playerResult.result.message}")
    }
}
