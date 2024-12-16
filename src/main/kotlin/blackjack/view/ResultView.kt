package blackjack.view

import blackjack.domain.BlackJackResult
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player

object ResultView {
    fun printPlayerInitMessage(names: List<String>) {
        val namesToString = names.joinToString(separator = ", ")
        println("딜러와 $namesToString 에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerWithCard(card: Card) {
        println("\n딜러: ${card.rank.rankName}${card.suit.koreanName}")
    }

    fun printPlayerNameWithCards(player: Player) {
        println(playerNameWithCards(player))
    }

    fun printDealerOneMoreCardDrawn() {
        println("\n=== 딜러는 16이하라 한장의 카드를 더 받았습니다. ===")
    }

    fun printFinalScoresForPlayer(player: Player) {
        println(
            buildString {
                append(playerNameWithCards(player))
                append(" - 결과: ${player.cardsSum}")
            },
        )
    }

    fun printFinalScoresForDealer(dealer: Dealer) {
        println("\n딜러 카드: ${dealer.cards.value.map { "${it.rank.rankName}${it.suit.koreanName}" }} - 결과: ${dealer.cardsSum}")
    }

    private fun playerNameWithCards(player: Player): String {
        return "${player.name} 카드: ${player.cards.value.map { "${it.rank.rankName}${it.suit.koreanName}" }}"
    }

    fun printFinalWinLose(result: BlackJackResult) {
        println("\n### 최종 승패")
        println("딜러 ${result.dealerWinCount}승 ${result.dealerLoseCount}패")
        result.playerToResultMap.value.forEach { (player, result) ->
            val winOrLose = if (result == GameResult.WIN) "승" else "패"
            println("${player.name} : $winOrLose")
        }
    }
}
