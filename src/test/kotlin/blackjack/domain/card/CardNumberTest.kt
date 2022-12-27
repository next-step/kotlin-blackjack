package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardNumberTest : FunSpec({
    context("예외로 Ace는 1 또는 11로 계산할 수 있다.") {
        withData(
            nameFn = { "$it" },
            listOf(CardNumber.ACE)
        ) { aceCardNumber ->
            aceCardNumber.number shouldBe 11
            aceCardNumber.candidateNumber shouldBe 1
            aceCardNumber shouldNotBe null
        }
    }

    context("King, Queen, Jack은 각각 10으로 계산한다.") {
        withData(
            nameFn = { "$it" },
            listOf(CardNumber.KING, CardNumber.QUEEN, CardNumber.JACK)
        ) { cardNumber ->
            cardNumber.number shouldBe 10
            cardNumber.candidateNumber shouldBe 10
            cardNumber shouldNotBe null
        }
    }

    context("1~10 카드 숫자를 가진다.") {
        val excludedCardNumbers = specialCardNumbers()
        withData(
            nameFn = { "$it" },
            CardNumber.values().filterNot { it in excludedCardNumbers }
        ) { cardNumber ->
            cardNumber.number shouldBeInRange (1..10)
            cardNumber.candidateNumber shouldBe cardNumber.number
            cardNumber shouldNotBe null
        }
    }
})

private fun specialCardNumbers(): List<CardNumber> =
    listOf(CardNumber.KING, CardNumber.QUEEN, CardNumber.JACK, CardNumber.ACE)
