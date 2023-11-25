package blackjack

import blackjack.CardsCompound.Companion.BEST

object ResultView {
    fun showCardShare(players: List<Player>) {
        println("딜러와 ${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
    }

    fun showCardScore(vararg players: Gamer) {
        for (player in players) {
            val resultText = getResultText(player.playerCards.getBestScore())
            println("${player.name}카드: ${player.playerCards.cardsToString()} - 결과: $resultText")
        }
    }

    fun showResult(players: List<Player>, dealer: Dealer) {
        println("## 최종 수익")
        showProfit(dealer)
        for (player in players) {
            showProfit(player)
        }
    }

    private fun showProfit(gamer: Gamer) {
        println("${gamer.name}: ${gamer.profit}")
    }

    fun showPlayerCards(player: Gamer) {
        println("${player.name}카드: ${player.playerCards.cardsToString()}")
    }

    fun showDealerDrawCount(count: Int) {
        if (count == 0) return
        println("딜러는 16이하라 ${count}장의 카드를 더 받았습니다.")
    }

    private fun getResultText(bestScore: Int): String {
        if (bestScore > BEST) return "버스트"
        return bestScore.toString()
    }

    fun showInitialCards(gamer: Gamer) {
        println("${gamer.name} 카드: ${gamer.initialPublicCards.cardsToString()}")
    }

    private fun PlayerCards.cardsToString(): String {
        return cards.joinToString { it.cardToString() }
    }

    private fun PlayingCard.cardToString(): String {
        return "${number.numberName}${suit.korean}"
    }
}
