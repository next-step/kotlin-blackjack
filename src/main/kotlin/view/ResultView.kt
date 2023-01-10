package view

import model.Card
import model.CardVendor
import model.Names

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

    fun showPlayerCardState(players: Map<String, List<Card>>) {
        var result = ""
        players.forEach {
            result += "${it.key}카드: "
            it.value.forEach { card ->
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

    fun showPlayerCardStateResult(players: Map<String, List<Card>>, cardVendor: CardVendor) {
        players.forEach {
            var result = "${it.key}카드: "
            it.value.forEach { card ->
                result += "${card.cardNumber}${card.cardShape}, "
            }
            result = result.substring(0, result.lastIndex - 1) + " - 결과: ${cardVendor.sumCardNumbers(it.value)}"
            println(result)
        }
    }
}
