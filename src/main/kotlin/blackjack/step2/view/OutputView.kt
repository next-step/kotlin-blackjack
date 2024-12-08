package blackjack.step2.view

import blackjack.step2.domain.Card
import blackjack.step2.domain.Player

object OutputView {
    fun printFirstDealtCard(players: List<Player>) {
        players.forEach { playerCard ->
            println("${playerCard.playerName} 카드: ${formatCards(playerCard.allCards)}")
        }
    }

    fun printCardResult(players: List<Player>) {
        players.forEach { playerCard ->
            println(
                "${playerCard.playerName} 카드: ${formatCards(playerCard.allCards)} (점수: ${playerCard.calculateScore()})",
            )
        }
    }

    private fun formatCards(cards: List<Card>): String {
        return cards.joinToString(", ") {
            "${it.number.denomination}${it.type.value}"
        }
    }
}
