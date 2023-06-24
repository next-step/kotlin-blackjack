package blackjack

import blackjack.CardType.Companion.toText
import blackjack.CardValue.Companion.toText

object OutputView {
    fun divideCard(names: List<String>) {
        println("${names.joinToString(",")}에게 2장의 카드를 나누었습니다.")
    }

    fun printCards(player: Player, isResult: Boolean = false) {
        val cards = player.cards.joinToString { "${it.value.toText()}${it.type.toText()}" }
        print("${player.name}카드: $cards")

        if (isResult) {
            print(" - 결과: ${player.sum}")
        }
        println()
    }

    fun enterLine() = println()

    fun printMessage(message: String) = println(message)
}
