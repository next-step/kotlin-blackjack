package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.ParticipantProfit
import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {

    fun printDefaultReceivedCards(players: List<Player>) {
        val playersToString = players.joinToString(", ") { it.name }
        println("\n$playersToString 에게 2장의 카드를 나누었습니다.")
        players.forEach { player -> printlnPlayerCards(player) }
        println()
    }

    fun printlnPlayerCards(player: Player) {
        printPlayerCards(player)
        println()
    }

    fun printScoreOfParticipants(players: Players, dealer: Dealer) {
        println()
        printDealerCardsWithScore(dealer)
        printPlayerCardsWithScore(players)
    }

    fun printlnDealerGetAdditionalCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(players: Players, dealer: Dealer) {
        println("\n## 최종 승패")
        printResultOfDealer(players, dealer)
        println()
        players.values.forEach {
            val result = GameResult.resultOfPlayer(it, dealer)
            print("${it.name}: ")
            when (result) {
                GameResult.WIN -> println("승")
                GameResult.LOSE -> println("패")
                else -> println("무")
            }
        }
    }

    fun printGameProfit(participantProfits: List<ParticipantProfit>) {
        println("\n## 최종 수익")
        participantProfits.forEach {
            println("${it.participantName}: ${it.profit.toInt()}")
        }
    }

    private fun printResultOfDealer(players: Players, dealer: Dealer) {
        val dealerResult = GameResult.resultOfDealer(players, dealer)
            .groupingBy { it }
            .eachCount()
        print("${dealer.name}: ")
        dealerResult.forEach { (result, count) ->
            when (result) {
                GameResult.WIN -> print("${count}승 ")
                GameResult.LOSE -> print("${count}패 ")
                else -> print("${count}무 ")
            }
        }
    }

    private fun printDealerCardsWithScore(dealer: Dealer) {
        val cards = cardsToString(dealer.cards)
        println("${dealer.name} 카드: $cards - 결과: ${dealer.score}")
    }

    private fun printPlayerCardsWithScore(players: Players) {
        players.values.forEach {
            printPlayerCards(it)
            println("- 결과: ${it.score}")
        }
    }

    private fun printPlayerCards(player: Player) {
        val playerName = player.name
        val cardsToString = cardsToString(player.cards)
        print("${playerName}카드: ")
        print(cardsToString)
    }

    private fun cardsToString(cards: Cards): String = cards.values
        .joinToString(", ") { card ->
            card.rank.symbol + card.suit.displayName
        }
}
