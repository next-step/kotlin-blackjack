package blackjack.view

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer

class OutputView {

    companion object {
        private const val PRINT_START_GAME = "에게 2장의 카드를 나누었습니다."
        private const val BLACKJACK_END_NUMBER = 21

        fun printStartGame(gamers: List<Gamer>) {
            val playerNames = gamers.joinToString { it.name }
            println("${playerNames}$PRINT_START_GAME")

            for (gamer in gamers) {
                if (gamer is Dealer) {
                    println("${gamer.name} 카드: ${gamer.cards.value[0]}")
                } else {
                    printGamerCard(gamer)
                }
            }
        }

        fun printGamerCard(gamer: Gamer) {
            println("${gamer.name} 카드: ${gamer.haveCards()}")
        }

        fun printBlackjackResult(gamers: List<Gamer>) {
            println("\n---블랙잭 결과---")
            for (gamer in gamers) {
                val blackjackResult = getBlackjackResult(gamer)
                println("${gamer.name}카드: ${gamer.haveCards()} - 결과: $blackjackResult")
            }
        }

        private fun getBlackjackResult(gamer: Gamer): String {
            val totalScore = gamer.cards.getTotalScore()
            return if (totalScore >= BLACKJACK_END_NUMBER) {
                gamer.state.toString()
            } else {
                totalScore.toString()
            }
        }

        fun printCurrentDealerScore(currentScore: Int) {
            return if (currentScore > 16) {
                println("딜러는 17이상이라 카드를 더 받을 수 없습니다.")
            } else {
                println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            }
        }

        fun printFinalOutcome(gamers: List<Gamer>) {
            println("## 최종 승패")
            for (gamer in gamers) {
                println("${gamer.name}: ${gamer.result()}")
            }
        }
    }
}
