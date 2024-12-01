package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class CardFactoryTest : StringSpec({
    "CardNumber 와 Suit 를 받아 Card를 만들어 반환한다" {
        val cardNumber = CardNumber.Ace
        val suit = Suit.SPADES

        val sut = CardFactory.from(cardNumber, suit)

        sut shouldBe Card(cardNumber, suit)
    }

    "CardFactory 는 Suit 와 CardNumber를 조합한 52장(4 * 13)의 카드 리스트를 반환할 수 있다" {
        val results = CardFactory.all()

        val expected =
            Suit.entries.flatMap { suit ->
                CardNumberFactory.all().map { cardNumber ->
                    Card(cardNumber, suit)
                }
            }

        results.size shouldBe 52
        results shouldContainExactlyInAnyOrder expected
    }
})
