package blackjack.view

import blackjack.domain.Player

object PlayerView {

    private val cardsTemplate = { player: Player -> "${player.name} 카드 : ${player.cards.joinToString()}" }

    fun printCards(player: Player) {
        println(cardsTemplate(player))
    }

    fun printCardsWithResult(player: Player) {
        println("${cardsTemplate(player)} - 결과: ${player.getPoints()}")
    }
}
