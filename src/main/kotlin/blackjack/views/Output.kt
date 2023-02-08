package blackjack.views

import blackjack.domains.deck.Cards
import blackjack.domains.participants.Gamers

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

    fun printResultEarningRate(gamers: Gamers) {
        val dealer = gamers.getDealer()
        val players = gamers.getPlayers()

        println("")
        println("## 최종 수익")
        println("${dealer.name}: ${dealer.earningAmount}")
        players.forEach { user -> println("${user.name}: ${user.earningAmount}") }
    }
}
