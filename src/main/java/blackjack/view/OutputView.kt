package blackjack.view

import blackjack.Player
import blackjack.card.Card
import blackjack.card.CardType

object OutputView {

    private fun CardType.getCardName(): String = when {
        this == CardType.SPADE -> "스페이드"
        this == CardType.HEART -> "하트"
        this == CardType.DIAMOND -> "다이아몬드"
        else -> "클로버"
    }

    fun showPlayersCard(players: List<Player>) {
        players.forEach(::showCard)
    }

    fun showCard(player: Player) {
        println("${player.name}카드: ${getCardNames(player.cards)}")
    }

    fun showResult(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${getCardNames(it.cards)} - 결과: ${it.getTotalSum()}") }
    }

    private fun getCardNames(cards: List<Card>): String {
        return cards.joinToString { "${it.cardValue.desc}${it.cardType.getCardName()}" }
    }

}
