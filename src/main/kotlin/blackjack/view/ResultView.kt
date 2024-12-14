package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player

object ResultView {
    fun printFinalScores(player: Player) {
        println(
            buildString {
                playerNameWithCards(player)
                append(" - 결과: ${player.cardsSum}")
            },
        )
    }

    fun printPlayerInitMessage(names: List<String>) {
        val namesToString = names.joinToString(separator = ", ")
        println("딜러와 $namesToString 에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerWithCard(card: Card) {
        println("딜러: ${card.rank.rankName}${card.suit.koreanName}")
    }

    fun printPlayerNameWithCards(player: Player) {
        println(playerNameWithCards(player))
    }

    private fun playerNameWithCards(player: Player): String {
        return "${player.name} 카드: ${player.cards.value.map { "${it.rank.rankName}${it.suit.koreanName}" }}"
    }

    fun printDealerOneMoreCardDrawn() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
