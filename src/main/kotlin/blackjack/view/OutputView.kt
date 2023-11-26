package blackjack.view

import blackjack.model.card.Card
import blackjack.model.game.BettingResult
import blackjack.model.game.MatchResult
import blackjack.model.game.Rank
import blackjack.model.player.Dealer
import blackjack.model.player.Player

object OutputView {
    fun printInitStatus(dealer: Dealer, players: List<Player>) {
        val names = players.joinToString { it.name }
        println("\n딜러와 ${names}에게 2장의 카드를 나누었습니다.")
        printDealerCard(dealer)
        players.forEach { player -> printPlayerCards(player) }
    }

    fun printPlayerCards(player: Player) {
        val card = getCard(player.cards())
        println("${player.name} 카드 : $card")
    }

    fun printResult(dealer: Dealer, players: List<Player>) {
        println()
        println("딜러 카드 : ${getCard(dealer.cards())} - 결과 : ${dealer.score()}")
        players.forEach { player ->
            val card = getCard(player.cards())
            val totalScore = player.score()
            println("${player.name} 카드 : $card - 결과 : $totalScore")
        }
    }

    fun printDealerPop() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printMatchResult(matchResult: MatchResult) {
        val dealerResult = matchResult.dealerResult.groupingBy { it }.eachCount()
        println("\n## 최종 승패")
        println("딜러 : ${dealerResult[Rank.WIN] ?: 0}승 ${dealerResult[Rank.DRAW] ?: 0}무 ${dealerResult[Rank.LOSE] ?: 0}패")
        matchResult.playerResults.forEach {
            println("${it.key.name} : ${it.value.rank}")
        }
    }

    fun printBettingResult(bettingResult: BettingResult) {
        println("\n## 최종 수익")
        println("딜러 : ${bettingResult.dealerBenefit}")
        bettingResult.playerBenefits.forEach {
            println("${it.key.name} : ${it.value}")
        }
    }

    private fun printDealerCard(dealer: Dealer) {
        val card = dealer.cards().last()
        println("딜러 : ${card.number}${card.suit.name}")
    }

    private fun getCard(cards: List<Card>) =
        cards.joinToString(separator = ", ") { "${it.number}${it.suit.name}" }
}
