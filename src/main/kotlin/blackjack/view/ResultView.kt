package blackjack.view

import blackjack.domain.Card
import blackjack.domain.GameResult
import blackjack.domain.PlayerResultDTO

object ResultView {
    fun showInitialCards(playerResults: List<PlayerResultDTO>) {
        println("플레이어들에게 2장의 카드를 나누었습니다.")
        playerResults.forEach { result ->
            println("${result.name}카드: ${result.cards}")
        }
    }

    fun showDealerInitialCard(firstCard: Card) {
        println("딜러: ${firstCard.rank}${firstCard.suit}")
    }

    fun showPlayerCards(playerResult: PlayerResultDTO) {
        println("${playerResult.name}카드: ${playerResult.cards}")
    }

    fun showDealerCards(dealerCards: List<Card>) {
        val cardDetails = dealerCards.joinToString { "${it.rank}${it.suit}" }
        println("딜러 카드: $cardDetails")
    }

    fun showFinalResults(gameResult: GameResult) {
        println("\n## 최종 승패")

        val (wins, losses) = gameResult.getDealerWinLoss()
        println("딜러: ${wins}승 ${losses}패")

        gameResult.playerResults.forEach { (playerName, result) ->
            val outcome = if (result == GameResult.Result.WIN) "승" else "패"
            println("$playerName: $outcome")
        }
    }
}
