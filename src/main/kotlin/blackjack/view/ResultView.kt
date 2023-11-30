package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Player

class ResultView {
    fun showInitialPlayers(dealer: Dealer, players: List<Player>) {
        println("\n딜러와 ${players.joinToString { it.name }}에게 ${BlackjackRule.INITIAL_CARD}장의 카드를 나누었습니다.")
        showDealer(dealer = dealer)
        players.forEach {
            showPlayer(player = it)
        }
        println()
    }

    private fun showDealer(dealer: Dealer) {
        println(dealerText(dealer = dealer))
    }

    fun showPlayer(player: Player) {
        println(playerText(player = player))
    }

    fun showResult(players: List<Player>) {
        println(
            players.joinToString("\n") {
                "${playerText(player = it)} - ${playerScore(player = it)}"
            }
        )
    }

    private fun dealerText(dealer: Dealer): String =
        "딜러: ${dealer.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun playerText(player: Player): String =
        "${player.name}카드: ${player.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun playerScore(player: Player): String = "결과: ${player.getScore()}"
}
