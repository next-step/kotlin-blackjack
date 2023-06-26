package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun printUserCardList(player: Player) {
        var cards = player.getCards().joinToString(", ")
        println("${player.getName()}카드: $cards")
    }
}
