package view

import controller.CardNumberCalculator
import model.Card
import model.Names
import model.Person

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
        println("딜러와 ${result}에게 2장의 카드를 나누었습니다.")
    }

    fun showPlayerCardState(players: List<Person>) {
        var result = ""
        players.forEach { player ->
            result += "${player.notifyName()}카드: "
            player.openCard().forEach { card ->
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

    fun showPlayerCardStateResult(players: List<Person>) {
        players.forEach { player ->
            var result = "${player.notifyName()}카드: "
            player.openCard().forEach { card ->
                result += "${card.cardNumber}${card.cardShape}, "
            }
            result = result.substring(0, result.lastIndex - 1) + " - 결과: ${CardNumberCalculator().sumCardNumbers(player.openCard())}"
            println(result)
        }
    }
}
