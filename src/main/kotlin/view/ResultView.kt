package view

import model.Card
import model.CardVendor
import model.Names
import model.Player

class ResultView {
    fun showDistributedCard(names: Names) {
        var result = ""
        for ((index, name) in names.values.withIndex()) {
            if (index == 0) {
                result += name
                continue
            }
            result += ", $name"
        }
        println("${result}에게 2장의 카드를 나누었습니다.")
    }

    fun showPlayerCardState(players: List<Player>) {
        var result = ""
        players.forEach { player ->
            result += "${player.getName()}카드: "
            player.getCard().forEach { card ->
                result += "${card.cardNumber}${card.cardShape}, "
            }
            result = result.substring(0, result.lastIndex - 1) + "\n"
        }
        print(result)
    }

    fun showSpecificUserCardState(name: String, cards: List<Card>) {
        var result = "${name}카드: "
        cards.forEach {
            result += "${it.cardNumber}${it.cardShape}, "
        }
        result = result.substring(0, result.lastIndex - 1) + "\n"
        println(result)
    }

    fun showPlayerCardStateResult(players: List<Player>, cardVendor: CardVendor) {
        players.forEach { player ->
            var result = "${player.getName()}카드: "
            player.getCard().forEach { card ->
                result += "${card.cardNumber}${card.cardShape}, "
            }
            result = result.substring(0, result.lastIndex - 1) + " - 결과: ${cardVendor.sumCardNumbers(player.getCard())}"
            println(result)
        }
    }
}
