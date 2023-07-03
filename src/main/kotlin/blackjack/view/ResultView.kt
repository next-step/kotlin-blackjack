package blackjack.view

import blackjack.domain.card.Cards
import blackjack.domain.game.BlackJack
import blackjack.domain.game.Results
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

object ResultView {

    private const val DEALER_CARD_STRING = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val START_STRING = "에게 2장의 나누었습니다."
    private const val CARD_STRING = "카드:"
    private const val SCORE_STRING = "- 결과:"
    private const val RESULT_STRING = "\n## 최종 수익"

    fun printPlayerCards(player: Player) {
        println("${player.name}$CARD_STRING ${getPrintCardString(player.cards)}")
    }

    fun printAddDealerCard() {
        println(DEALER_CARD_STRING)
    }

    private fun printDealerInitCards(dealer: Dealer) {
        val firstCard = dealer.cards.values.first()
        println("${dealer.name}$CARD_STRING ${firstCard.character.value + firstCard.shape.value}")
    }

    fun printFirstCards(game: BlackJack) {
        println("\n${game.dealer.name}와 ${game.players.joinToString { it.name }}$START_STRING")
        printDealerInitCards(game.dealer)
        game.players.forEach { printPlayerCards(it) }
        println()
    }

    fun printScore(game: BlackJack) {
        println()
        printDealerScore(game.dealer)
        game.players.forEach { printPlayerScore(it) }
    }

    private fun printDealerScore(dealer: Dealer) {
        println("${dealer.name}$CARD_STRING ${getPrintCardString(dealer.cards)} $SCORE_STRING ${dealer.score()}")
    }

    private fun printPlayerScore(player: Player) {
        println("${player.name}$CARD_STRING ${getPrintCardString(player.cards)} $SCORE_STRING ${player.score()}")
    }

    fun printResult(game: BlackJack, results: Results) {
        println(RESULT_STRING)
        println("${game.dealer.name}: ${results.getDealerResultAmount()}")
        results.revenues.forEach { (player, revenue) -> println("${player.name}: $revenue") }
    }

    private fun getPrintCardString(cards: Cards) = cards.values.joinToString { it.character.value + it.shape.value }
}
