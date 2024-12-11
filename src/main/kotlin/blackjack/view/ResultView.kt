package blackjack.view

import blackjack.domain.Card
import blackjack.domain.PlayerResultDTO

object ResultView {
    fun showInitialCards(playerResults: List<PlayerResultDTO>) {
        println("플레이어들에게 2장의 카드를 나누었습니다.")
        playerResults.forEach { result ->
            println("${result.name}카드: ${result.cards}")
        }
    }

    fun showDealerInitialCard(card: Card) {
        println("딜러: ${card.rank}${card.suit}")
    }

    fun showPlayerCards(playerResult: PlayerResultDTO) {
        println("${playerResult.name}카드: ${playerResult.cards}")
    }

    fun showDealerCards(cards: List<Card>) {
        println("딜러 카드: ${cards.joinToString { "${it.rank}${it.suit}" }}")
    }

    fun showFinalProfits(profits: Map<String, Int>) {
        println("\n## 최종 수익")
        profits.forEach { (name, profit) ->
            println("$name: $profit")
        }
    }
}
