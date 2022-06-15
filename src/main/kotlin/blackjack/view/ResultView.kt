package blackjack.view

import blackjack.domain.blackjack.BlackJackResult
import blackjack.domain.blackjack.ParticipantProfitResult
import blackjack.domain.card.Card
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {

    fun printInit(players: Players, dealer: Dealer) {
        println("${dealer.name}와 ${players.players.map(Player::name).joinToString(",")}에게 2장의 나누었습니다.")
    }

    fun printCards(players: Players, dealer: Dealer) {
        printlnPlayerWithCards(dealer.name, dealer.cards.cards.take(1))
        players.players.forEach { player ->
            printlnPlayerWithCards(player.name, player.cards.cards)
        }.also { println() }
    }

    fun printlnPlayerWithCards(name: String, cards: List<Card>) {
        printPlayerWithCards(name, cards).also { println() }
    }

    fun printCardsWithScore(players: Players, dealer: Dealer) {
        println()
        printParticipantWithScore(dealer)
        players.players.forEach {
            printParticipantWithScore(it)
        }.also { println() }
    }

    private fun printParticipantWithScore(participant: Participant) {
        printPlayerWithCards(participant.name, participant.cards.cards)
        println(" - 결과: ${participant.cards.score().value}")
    }

    fun printProfits(result: BlackJackResult) {
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
