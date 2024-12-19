package blackjack.view

import blackjack.domain.BlackJackResult
import blackjack.domain.Card
import blackjack.domain.Dealer
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

    fun printFinalProfit(result: BlackJackResult) {
        println("\n### 최종 수익")
        println("딜러 : ${result.dealerProfitMoney.getCurrentProfit()}")
        result.playerToProfit.value.forEach { (player, profit) ->
            println("${player.name} : ${profit.getCurrentProfit()}")
        }
    }
}
