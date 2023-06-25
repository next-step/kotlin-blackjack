package blackjack.view

import blackjack.domain.model.CardType.Companion.toText
import blackjack.domain.model.CardValue.Companion.toText
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player

object OutputView {
    fun divideCard(names: List<String>) {
        println("${names.joinToString(",")}에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayersCards(players: List<Player>, isResult: Boolean = false) {
        if (isResult) println()
        players.forEach {
            printCards(it, isResult)
        }
        println()
    }

    fun printCards(player: Player, isResult: Boolean = false) {
        val cards = if (player is Dealer && !isResult) {
            val card = player.cards.items[0]
            "${card.value.toText()}${card.type.toText()}"
        } else player.cards.items.joinToString { "${it.value.toText()}${it.type.toText()}" }
        print("${player.info.name}${if (player is Dealer) "" else "카드"}: $cards")

        if (isResult) {
            print(" - 결과: ${player.cards.sum}")
        }
        println()
    }
}
