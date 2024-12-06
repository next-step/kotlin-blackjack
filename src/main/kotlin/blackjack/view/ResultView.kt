package blackjack.view

import blackjack.domain.PlayerResultDTO

object ResultView {
    fun showInitialCards(playerResults: List<PlayerResultDTO>) {
        println("플레이어들에게 2장의 카드를 나누었습니다.")
        playerResults.forEach { result ->
            println("${result.name}카드: ${result.cards}")
        }
    }

    fun showPlayerCards(playerResult: PlayerResultDTO) {
        println("${playerResult.name}카드: ${playerResult.cards}")
    }

    fun showFinalResults(playerResults: List<PlayerResultDTO>) {
        println("\n최종 결과:")
        playerResults.forEach { result ->
            println("${result.name}카드: ${result.cards} - 결과: ${result.totalValue}")
        }
    }
}
