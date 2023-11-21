package blackjack

import java.util.EnumMap

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

    fun showResult(result: GameResult) {
        println("## 최종 승패")
        showDealerResult(result.dealerResult)
        for (playerResult in result.playerResult) {
            println("${playerResult.name}: ${playerResult.matchResult.korean}")
        }
    }

    private fun showDealerResult(result: EnumMap<MatchResult, Int>) {
        print("딜러: ")
        result[MatchResult.WIN]?.let {
            print("${it}승 ")
        }
        result[MatchResult.DRAW]?.let {
            print("${it}무 ")
        }
        result[MatchResult.LOSE]?.let {
            print("${it}패 ")
        }
        println()
    }

    fun showPlayerCards(player: Gamer) {
        println("${player.name}카드: ${player.playerCards.cardsToString()}")
    }

    fun showDealerDrawCount(count: Int) {
        if (count == 0) return
        println("딜러는 16이하라 ${count}장의 카드를 더 받았습니다.")
    }

    private fun getResultText(bestScore: Int): String {
        if (bestScore == CardsCompound.BUSTED) return "버스트"
        return bestScore.toString()
    }

    private fun PlayerCards.cardsToString(): String {
        return cards.joinToString { it.cardToString() }
    }

    private fun PlayingCard.cardToString(): String {
        return "${number.numberName}${suit.korean}"
    }

    fun showDealerCard(dealer: Gamer) {
        println("딜러 카드: ${dealer.playerCards.cards.first().cardToString()}")
    }
}
