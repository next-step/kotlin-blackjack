package view

import model.CardNumberCalculator
import model.Dealer
import model.Names
import model.Participant

object ResultView {
    fun showDistributedCard(names: Names) {
        var result = ""
        for ((index, name) in names.values.withIndex()) {
            if (index == 0) {
                result += name
                continue
            }
            result += ", $name"
        }
        println("딜러와 ${result}에게 2장의 카드를 나누었습니다.")
    }

    fun showPlayersCardState(players: List<Participant>) {
        players.forEach { player ->
            showPlayerCardState(player)
        }
    }

    fun showPlayerCardState(player: Participant) {
        var result = ""
        result += "${player.name}카드: "
        player.cards.forEach { card ->
            result += "${card.cardNumber}${card.cardShape}, "
        }
        result = result.substring(0, result.lastIndex - 1)
        println(result)
    }

    fun showPlayersCardStateResult(players: List<Participant>) {
        players.forEach { player ->
            showPlayerCardStateResult(player)
        }
    }

    fun showPlayerCardStateResult(player: Participant) {
        var result = "${player.name}카드: "
        player.cards.forEach { card ->
            result += "${card.cardNumber}${card.cardShape}, "
        }
        result = result.substring(
            0,
            result.lastIndex - 1
        ) + " - 결과: ${CardNumberCalculator.sumCardNumbers(player.cards)}"
        println(result)
    }

    fun showGameResult(dealer: Dealer, players: List<Participant>) {
        println("\n## 최종 승패")
        showPersonalGameResult(dealer)
        players.forEach { player ->
            showPersonalGameResult(player)
        }
    }

    private fun showPersonalGameResult(dealer: Participant) {
        val result =
            "${dealer.name}: ${dealer.notifyGameResultState().win}승 ${dealer.notifyGameResultState().lose}패 ${dealer.notifyGameResultState().draw}무"
        println(result)
    }

    fun showReceivedCardByDealer() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }
}
