package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.dto.BlackjackGameResult
import blackjack.service.BlackjackService

object ResultView {

    fun printPlayers(players: List<Player>, dealer: Dealer) {
        val playersName = players.joinToString { it.name }
        println("${dealer.name}와 ${playersName}에게 ${BlackjackService.BASIC_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    fun printPlayersAndCards(players: List<Player>, dealer: Dealer) {
        println("${dealer.name}: ${dealer.cards.first().cardInfo()}")
        players.forEach { player ->
            val cardsInfo = getCardsInfo(player.cards)
            println("${player.name}카드: $cardsInfo")
        }
    }

    fun printPlayerAndCards(player: Player) {
        val cardsInfo = getCardsInfo(player.cards)
        println("${player.name}카드: $cardsInfo")
    }

    fun printResultScore(players: List<Player>, dealer: Dealer) {

        val dealerScore = dealer.cards.calculateScore()
        println("${dealer.name} 카드: ${getCardsInfo(dealer.cards)} - 결과: ${dealerScore.value}")

        players.forEach { player ->
            val cardsInfo = getCardsInfo(player.cards)
            val totalScore = player.cards.calculateScore()
            println("${player.name}카드: $cardsInfo - 결과: ${totalScore.value}")
        }
    }

    fun printDealerCard(dealer: Dealer) {
        if (dealer.cards.calculateScore().value > Score.STANDARD_CARD_SCORE) {
            println("${dealer.name}는 ${Score.STANDARD_CARD_SCORE + 1}이상이라 카드를 더 받지 않습니다.")
        } else if (dealer.cards.calculateScore().value < Score.STANDARD_CARD_SCORE) {
            println("${dealer.name}는 ${Score.STANDARD_CARD_SCORE}이하라 한장의 카드를 더 받았습니다.")
        }
    }

    fun printResultGame(result: List<BlackjackGameResult>) {
        println("## 최종 승패")
        result.forEach {
            println("${it.name}: ${it.win} ${it.draw} ${it.lose}")
        }
    }

    private fun getCardsInfo(cards: Cards): String {
        var cardsInfo = ""

        cards.cards.forEachIndexed { index, card ->
            cardsInfo += "${card.rank.rankName}${card.symbol.symbolName}"
            if (index != cards.cards.lastIndex) {
                cardsInfo += ", "
            }
        }
        return cardsInfo
    }

    fun printEnter() {
        println()
    }
}
