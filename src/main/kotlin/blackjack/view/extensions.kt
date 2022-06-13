package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Suite

private val suiteMap = mapOf(
    Suite.HEARTS to "하트",
    Suite.DIAMONDS to "다이아몬드",
    Suite.CLUBS to "클로버",
    Suite.SPADES to "스페이드"
)

private val denominationMap = mapOf(
    Denomination.ACE to "A",
    Denomination.TWO to "2",
    Denomination.THREE to "3",
    Denomination.FOUR to "4",
    Denomination.FIVE to "5",
    Denomination.SIX to "6",
    Denomination.SEVEN to "7",
    Denomination.EIGHT to "8",
    Denomination.NINE to "9",
    Denomination.TEN to "10",
    Denomination.JACK to "J",
    Denomination.QUEEN to "Q",
    Denomination.KING to "K",
)

fun Card.text(): String = "${denominationMap[denomination]}${suiteMap[suite]}"

fun Player.text(): String = "${name}카드: ${hand.cards.joinToString(", ") { it.text() }}"

fun Player.textFirstCard(): String = "${name}카드: ${hand.cards.firstOrNull()?.text() ?: ""}"
