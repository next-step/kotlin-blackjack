package blackjack.views

import blackjack.GameScoreType
import blackjack.domains.deck.Cards
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Player

object Output {
    fun printFirstDrawCards(playerNames: List<String>, drawCount: Int) {
        println("${playerNames.joinToString { it }} 에게 $drawCount 장을 나누었습니다.")
    }

    fun printHaveCards(playerName: String, cards: Cards) {
        println("$playerName 카드: $cards")
    }

    fun printSummedCards(playerName: String, cards: Cards, summed: Int) {
        println()
        println("$playerName 카드: $cards - 결과: $summed")
    }

    fun printDealerDraw() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printVictoryOrDefeat(dealer: Dealer, players: List<Player>) {
        println("")
        println("## 최종 승패")
        println(
            "딜러: ${dealer.getScoreCounts(GameScoreType.WIN)}승 ${dealer.getScoreCounts(GameScoreType.DRAW)}무 " +
                "${dealer.getScoreCounts(GameScoreType.LOSE)}패"
        )
        players.forEach {
            println("${ it.name }: ${it.gameScore.displayName}")
        }
    }
}
