package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerRank
import blackjack.domain.Ranks

object ResultView {

    private const val DEALER_CARD_STRING = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val START_STRING = "에게 2장의 나누었습니다."

    private const val CARD_STRING = "카드:"

    private const val SCORE_STRING = "- 결과:"

    fun printCards(player: Player) {
        println("${player.name}$CARD_STRING ${getPrintCardString(player.cards)}")
    }

    private fun printDealerCards(dealer: Dealer) {
        println("${dealer.name}$CARD_STRING ${getPrintCardString(dealer.cards)}")
    }

    fun printFirstCards(game: BlackJack) {
        println("\n${game.players.joinToString { it.name }}$START_STRING")
        printDealerCards(game.dealer)
        game.players.forEach { printCards(it) }
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

    fun printResult(game: BlackJack, ranks: Ranks) {
        println("\n## 최종 승패")
        println("${game.dealer.name}: ${ranks.values.count { it.value == PlayerRank.LOST }}${PlayerRank.LOST.dealerResult} ${ranks.values.count { it.value == PlayerRank.WON }}${PlayerRank.WON.dealerResult}")
        game.players.forEach { player -> ranks.values[player]?.let { rank -> println("${player.name}: ${rank.playerResult}") } }
    }

    private fun getPrintCardString(cards: Cards) = cards.value.joinToString { it.character.value + it.shape.value }
    fun printAddDealerCard() {
        println(DEALER_CARD_STRING)
    }
}
