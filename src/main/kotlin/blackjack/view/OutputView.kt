package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerMatchResult

internal class OutputView {
    fun renderCards(dealer: Dealer, players: List<Player>) {
        val names = players.joinToString(SEPARATOR) { it.name }
        println("\n${dealer.name}와 ${names}에게 2장의 나누었습니다.")

        renderPlayerCards(dealer)

        players.forEach {
            renderPlayerCards(it)
        }
        println()
    }

    fun renderPlayerCards(player: Player) {
        val cardNames = player.visibleCards.map { it.name }.joinToString(SEPARATOR)
        println("${player.name}카드: $cardNames")
    }

    fun renderDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun renderCardsAndScore(dealer: Dealer, players: List<Player>) {
        println()
        renderPlayerCardsAndScore(dealer)
        players.forEach(this::renderPlayerCardsAndScore)
    }

    fun renderResult(result: PlayerMatchResult) {
        println("\n## 최종 승패")

        val dealerResult = result.dealerResults.groupBy { it }.map { "${it.value.size}${it.key.desc}" }.joinToString(SEPARATOR)
        println("딜러: $dealerResult")

        result.playerResult.forEach {
            println("${it.key.name}: ${it.value.desc}")
        }
    }

    private fun renderPlayerCardsAndScore(player: Player) {
        val cardNames = player.cards.map { it.name }.joinToString(SEPARATOR)
        println("${player.name}카드: $cardNames - 결과: ${player.score()}")
    }

    companion object {
        private const val SEPARATOR = ", "
    }
}
