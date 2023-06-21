package blackjack.view

import blackjack.domain.Cards

class BlackjackView {
    fun printInitialTurn(names: List<String>) {
        println("${names.joinToString(", ")}에게 2장을 나누었습니다.")
    }

    fun printPlayersCard(name: String, cards: Cards) {
        println("${name}카드: ${cards.cards.joinToString(", ")}")
    }
}
