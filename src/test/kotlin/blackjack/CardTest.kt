package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "각 카드의 모양은 스페이드, 클로버, 하트, 다이아몬드가 존재한다" {
        Suit.entries.forEach { suit ->
            val sut = Card(CardNumber.Ace, suit)
            sut.suit shouldBe suit
        }
    }

    "각 카드는 자신의 숫자를 반환할 수 있다" {
        val suit = Suit.SPADES

        CardNumberFactory.all().forEach {
            val sut = Card(it, suit)
            sut.number() shouldBe it.baseValue
        }
    }

    "카드 목록은 CardNumber를 비교하여 CardNumber의 baseValue 가 낮은 순으로 정렬할 수 있다" {
        val sut =
            listOf(
                Card(Number(10), Suit.SPADES),
                Card(CardNumber.Ace, Suit.HEARTS),
                Card(Number(7), Suit.CLUBS),
            )

        val result = sut.sorted()

        result shouldContainExactly
            listOf(
                Card(CardNumber.Ace, Suit.HEARTS),
                Card(Number(7), Suit.CLUBS),
                Card(Number(10), Suit.SPADES),
            )
    }
})
