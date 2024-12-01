package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class CardNumberFactoryTest : StringSpec({
    "Ace, Jack, Queen, King 은 미리 상수화한 것을 반환한다" {
        CardNumberFactory.ACE shouldBe CardNumber.Ace
        CardNumberFactory.JACK shouldBe CardNumber.Jack
        CardNumberFactory.QUEEN shouldBe CardNumber.Queen
        CardNumberFactory.KING shouldBe CardNumber.King
    }

    "나머지 2~10까지는 CardNumber를 상속한 Number 객체를 만들어 반환한다" {
        (2..10).forEach {
            CardNumberFactory.number(it) shouldBe Number(it)
        }
    }
    "2 부터 10 사이에 있지 않은 숫자로 CardNumber 의 Number 객체를 만드려고 하면 예외를 던진다" {
        listOf(-1, 11).forEach {
            shouldThrow<IllegalArgumentException> {
                CardNumberFactory.number(it)
            }
        }
    }

    "CardNumberFactory 를 사용해 모든 CardNumber(Ace, Jack, Queen, King, 2~10)에 대한 리스트를 반환한다" {
        val results: List<CardNumber> = CardNumberFactory.all()
        results.size shouldBe 13
        results shouldContainExactlyInAnyOrder
            listOf(
                CardNumber.Ace,
                CardNumber.Jack,
                CardNumber.Queen,
                CardNumber.King,
                Number(2),
                Number(3),
                Number(4),
                Number(5),
                Number(6),
                Number(7),
                Number(8),
                Number(9),
                Number(10),
            )
    }
})
