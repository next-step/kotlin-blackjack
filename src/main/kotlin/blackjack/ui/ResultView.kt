package blackjack.ui

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Players

class ResultView {
    fun printStartMessage(players: Players) {
        val playersName = StringBuilder()
        players.forEach { player ->
            playersName.append(player.name + ",")
        }
        playersName.deleteCharAt(playersName.lastIndex)
        println("딜러와 ${playersName}에게 2장씩 나누었습니다.")
    }

    fun printGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        dealer.showAllCards()
        println(" - 결과: ${dealer.calculateCard()}")

        players.forEach { player ->
            player.showCards()
            println(" - 결과: ${player.calculateCard()}")
        }

        println("\n## 최종 승패")
        showWinningResult(dealer)
        players.forEach { player ->
            showWinningResult(player)
        }
    }

    private fun showWinningResult(player: Participant) {
        print("${player.name}: ")
        player.showGameResult()
    }

    fun printDealerDrawExtra(participant: Participant) {
        if (participant.name != Dealer.DEALER_NAME) return
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }
}
