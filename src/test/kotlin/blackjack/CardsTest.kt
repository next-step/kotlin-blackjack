package blackjack

import blackjack.Denomination.ACE
import blackjack.Denomination.JACK
import blackjack.Denomination.KING
import blackjack.Denomination.QUEEN
import blackjack.Denomination.THREE
import blackjack.Denomination.TWO
import blackjack.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    context("init") {
        test("중복된 카드가 저장될 경우 예외가 발생한다.") {
            val givenCards = mutableListOf(Card(SPADE, ACE), Card(SPADE, ACE))
            val exception = shouldThrowExactly<IllegalArgumentException> { Cards(givenCards) }
            exception.message shouldBe "중복된 카드가 저장될 수 없다."
        }

        test("카드 리스트를 생성한다.") {
            val actual = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, TWO)))
            actual.values() shouldHaveSize 2
        }
    }

    context("deal") {
        test("2장이상의 카드가 있는데 deal하는 경우 예외가 발생한다.") {
            val cards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, TWO)))
            val exception = shouldThrowExactly<IllegalStateException> { cards.deal(Card(SPADE, THREE)) }
            exception.message shouldBe "이미 2장이상 가지고 있어 deal할 수 없다."
        }

        test("중복된 카드를 deal하는 경우 예외가 발생한다.") {
            val cards = Cards(mutableListOf(Card(SPADE, ACE)))
            val exception = shouldThrowExactly<IllegalArgumentException> { cards.deal(Card(SPADE, ACE)) }
            exception.message shouldBe "이미 존재하는 카드를 추가할 수 없다."
        }

        test("카드를 deal할 수 있다.") {
            val cards = Cards()
            cards.deal(Card(SPADE, THREE))
            cards.values() shouldHaveSize 1
        }
    }

    context("hit") {
        test("2장미만의 카드가 있는데 hit하는 경우 예외가 발생한다") {
            val cards = Cards(mutableListOf(Card(SPADE, ACE)))
            val exception = shouldThrowExactly<IllegalStateException> { cards.hit(Card(SPADE, TWO)) }
            exception.message shouldBe "카드가 deal이 완료되지 않아 hit할 수 없다."
        }

        test("최소 계산된 점수가 21점 초과되는데 hit하는 경우 예외가 발생한다") {
            val cards = Cards(mutableListOf(Card(SPADE, KING), Card(SPADE, QUEEN), Card(SPADE, JACK)))
            val exception = shouldThrowExactly<IllegalStateException> { cards.hit(Card(SPADE, ACE)) }
            exception.message shouldBe "카드가 bust되어 더이상 hit할 수 없다."
        }

        test("중복된 카드를 hit하는 경우 예외가 발생한다.") {
            val cards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, TWO)))
            val exception = shouldThrowExactly<IllegalArgumentException> { cards.hit(Card(SPADE, ACE)) }
            exception.message shouldBe "이미 존재하는 카드를 추가할 수 없다."
        }

        test("카드를 hit 할 수 있다.") {
            val cards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, KING), Card(SPADE, JACK)))
            cards.hit(Card(SPADE, QUEEN))
            cards.values() shouldHaveSize 4
        }
    }

    context("of") {
        test("card를 받아 생성한다.") {
            val actual = Cards.of(Card(SPADE, ACE), Card(SPADE, KING))
            actual.values() shouldHaveSize 2
        }
    }
})
