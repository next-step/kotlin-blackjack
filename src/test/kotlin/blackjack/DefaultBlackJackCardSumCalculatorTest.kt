package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe

class DefaultBlackJackCardSumCalculatorTest : StringSpec({
    "블랮잭 카드의 숫자 합을 구할 수 있다(Ace 0개)" {
        forAll(*BlackJackCardTestFixtures.ace0Rows.toTypedArray()) { initialCards, expected ->
            val sut = DefaultBlackJackCardSumCalculator()
            val result = sut.sum(initialCards)
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 1개)" {
        forAll(*BlackJackCardTestFixtures.ace1Rows.toTypedArray()) { initialCards, expected ->
            val sut = DefaultBlackJackCardSumCalculator()
            val result = sut.sum(initialCards)
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 2개)" {
        forAll(*BlackJackCardTestFixtures.ace2Rows.toTypedArray()) { initialCards, expected ->
            val sut = DefaultBlackJackCardSumCalculator()
            val result = sut.sum(initialCards)
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 3개)" {
        forAll(*BlackJackCardTestFixtures.ace3Rows.toTypedArray()) { initialCards, expected ->
            val sut = DefaultBlackJackCardSumCalculator()
            val result = sut.sum(initialCards)
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 4개)" {
        forAll(*BlackJackCardTestFixtures.ace4Rows.toTypedArray()) { initialCards, expected ->
            val sut = DefaultBlackJackCardSumCalculator()
            val result = sut.sum(initialCards)
            result shouldBe expected
        }
    }
})
