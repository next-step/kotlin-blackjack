package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({
    "기본 숫자 CardNumber 들은 해당 숫자가 기본값이다" {
        (2..10).forEach {
            val sut = Number(it)
            sut.baseValue shouldBe it
        }
    }

    "각 카드 숫자 중 10, Jack, Queen, King는 기본값이 10이다" {
        listOf(Number(10), CardNumber.Jack, CardNumber.Queen, CardNumber.King).forEach { cardNumber ->
            cardNumber.baseValue shouldBe 10
        }
    }

    "Ace는 기본값이 1이다" {
        val sut = CardNumber.Ace
        sut.baseValue shouldBe 1
    }

    "CardNumber 리스트는 CardNumber 의 baseValue 를 비교해서 정렬할 수 있다" {
        val sut = listOf(Number(10), Number(4), CardNumber.Ace)
        val result = sut.sorted()
        result shouldContainExactly listOf(CardNumber.Ace, Number(4), Number(10))
    }
})
