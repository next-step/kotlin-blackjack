package blakjack.view

import blakjack.domain.Card
import blakjack.domain.CardRank
import blakjack.domain.CardSuit
import blakjack.domain.Cards
import blakjack.domain.Dealer
import blakjack.domain.Participant
import blakjack.domain.Player

object OutputView {
    fun printIntro(participants: List<Participant>) {
        println()
        println("${participants.joinToString { it.name }}에게 2장의 나누었습니다.")
    }

    fun printCards(participant: Participant) {
        println(getPrintPlayerCards(participant.name, participant.cards))
    }

    fun printCardsWithScore(participants: List<Participant>) {
        participants.forEach {
            println("${getPrintPlayerCards(it.name, it.cards)} - 결과: ${it.score}")
        }
    }

    private fun getPrintPlayerCards(name: String, cards: Cards): String {
        return "${name}카드: ${cards.values.joinToString(",") { it.korean() }}"
    }

    private fun Card.korean(): String {
        return "${CARD_RANK_KOREAN_MAP[rank]}${CARD_SUIT_KOREAN_MAP[suit]}"
    }

    fun printDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printResult(dealer: Dealer, player: List<Player>) {
        println()
        println("## 최종 승패")
        printDealerResult(dealer)
        player.forEach { printPlayerResult(it) }
    }

    private fun printDealerResult(dealer: Dealer) {
        println("${dealer.name}: ${dealer.winCount}승 ${dealer.loseCount}패")
    }

    private fun printPlayerResult(player: Player) {
        println("${player.name}: ${RESULT_KOREAN_MAP[player.result]}")
    }
}

private val CARD_RANK_KOREAN_MAP = mapOf(
    CardRank.ACE to "A",
    CardRank.TWO to "2",
    CardRank.THREE to "3",
    CardRank.FOUR to "4",
    CardRank.FIVE to "5",
    CardRank.SIX to "6",
    CardRank.SEVEN to "7",
    CardRank.EIGHT to "8",
    CardRank.NINE to "9",
    CardRank.TEN to "10",
    CardRank.JACK to "J",
    CardRank.QUEEN to "Q",
    CardRank.KING to "K",
)

private val CARD_SUIT_KOREAN_MAP = mapOf(
    CardSuit.HEART to "하트",
    CardSuit.DIAMOND to "다이아몬드",
    CardSuit.SPADE to "스페이드",
    CardSuit.CLOVER to "클로버",
)

private val RESULT_KOREAN_MAP = mapOf(
    Player.Result.WIN to "승",
    Player.Result.LOSE to "패",
)
