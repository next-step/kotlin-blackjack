package view

import model.Card
import model.CardNumber
import model.CardShape

class ResultView {
    fun showDistributedCard(names: List<String>) {
        var result = ""
        for ((index, name) in names.withIndex()) {
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
            it.value.forEach { it ->
                result += "${CardNumber.convertToString(it.cardNumber)}${CardShape.convertToString(it.cardShape)}, "
            }
            result = result.substring(0, result.lastIndex - 1) + "\n"
        }
        print(result)
    }

    fun showSpecificUserCardState(name: String, cards: List<Card>) {
        var result = "${name}카드: "
        cards.forEach {
            result += "${CardNumber.convertToString(it.cardNumber)}${CardShape.convertToString(it.cardShape)}, "
        }
        result = result.substring(0, result.lastIndex - 1) + "\n"
        println(result)
    }

    fun showPlayerCardStateResult(name: String, cards: List<Card>, total: Int) {
        var result = "${name}카드: "
        cards.forEach {
            result += "${CardNumber.convertToString(it.cardNumber)}${CardShape.convertToString(it.cardShape)}, "
        }
        result = result.substring(0, result.lastIndex - 1) + " - 결과: $total"
        println(result)
    }
}
