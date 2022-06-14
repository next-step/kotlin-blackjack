package blackjack.view

import blackjack.domain.blackjack.BlackJackResult
import blackjack.domain.blackjack.ParticipantProfitResult
import blackjack.domain.blackjack.ParticipantResult
import blackjack.domain.card.Card
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object ResultView {

    fun printlnBlackJackInit(players: List<Player>, dealer: Dealer) {
        println("${dealer.name}와 ${players.map(Player::name).joinToString(",")}에게 2장의 나누었습니다.")
    }

    fun printlnPlayersWithCards(players: List<Player>, dealer: Dealer) {
        printlnPlayerWithCards(dealer.name, dealer.cards.cards.take(1))
        players.forEach { player ->
            printlnPlayerWithCards(player.name, player.cards.cards)
        }.also { println() }
    }

    fun printlnPlayerWithCards(name: String, cards: List<Card>) {
        printPlayerWithCards(name, cards).also { println() }
    }

    fun printResult(result: BlackJackResult) {
        println()
        val dealer = result.dealerResult()
        printParticipantWithScore(dealer)
        result.playersResult().forEach {
            printParticipantWithScore(it)
        }.also { println() }
    }

    private fun printParticipantWithScore(participantResult: ParticipantResult) {
        printPlayerWithCards(participantResult.name, participantResult.cards.cards)
        println(" - 결과: ${participantResult.cards.score().value}")
    }

    fun printMatch(result: BlackJackResult) {
        println("## 최종 수익")
        printProfits(listOf(result.dealerProfit()) + result.playersProfit())
    }

    private fun printProfits(participantProfitResults: List<ParticipantProfitResult>) {
        participantProfitResults.forEach {
            println("${it.name}: ${it.profit.amount.toInt()}")
        }
    }

    fun printDealerPlay(dealerAddCount: Int) {
        println()
        if (dealerAddCount > 0) {
            println("딜러는 16이하라 ${dealerAddCount}장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 추가 카드를 받지 않았습니다.")
        }
    }

    private fun printPlayerWithCards(name: String, cards: List<Card>) {
        print("${name}카드: ${cards.joinToString(", ") { "${it.denomination.name}${convertSuit(it.suit)}" }}")
    }

    private fun convertSuit(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLOVER -> "클로버"
        }
    }
}
