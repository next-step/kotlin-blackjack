package view

import model.CardNumber
import model.CardShape
import model.Players

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

    fun showPlayerCardState(players: Players) {
        var result = ""
        players.get().forEach {
            result += "${it.key}카드: "
            it.value.forEach { it ->
                result += "${CardNumber.convertToString(it.cardNumber)}${CardShape.convertToString(it.cardShape)}, "
            }
            result = result.substring(0, result.lastIndex - 1) + "\n"
        }
        print(result)
    }
}
