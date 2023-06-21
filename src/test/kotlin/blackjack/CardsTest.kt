package blackjack

import blackjack.CardTest.Companion.SPADE_ACE
import blackjack.CardTest.Companion.SPADE_JACK
import blackjack.CardTest.Companion.SPADE_KING
import blackjack.CardTest.Companion.SPADE_QUEEN
import blackjack.CardTest.Companion.SPADE_TWO
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    context("init") {
        test("중복된 카드가 저장될 경우 예외가 발생한다.") {
            val givenCards = mutableListOf(SPADE_ACE, SPADE_ACE)
            val exception = shouldThrowExactly<IllegalArgumentException> { Cards(givenCards) }
            exception.message shouldBe "중복된 카드가 저장될 수 없다."
        }

        test("카드 리스트를 생성한다.") {
            val actual = Cards(mutableListOf(SPADE_ACE, SPADE_TWO))
            actual.values() shouldHaveSize 2
        }
    }

    context("deal") {
        test("2장이상의 카드가 있는데 deal하는 경우 예외가 발생한다.") {
            val cards = Cards.of(SPADE_ACE, SPADE_TWO)
            val exception = shouldThrowExactly<IllegalStateException> { cards.deal(SPADE_KING) }
            exception.message shouldBe "이미 2장이상 가지고 있어 deal할 수 없다."
        }

        test("중복된 카드를 deal하는 경우 예외가 발생한다.") {
            val cards = Cards.of(SPADE_ACE)
            val exception = shouldThrowExactly<IllegalArgumentException> { cards.deal(SPADE_ACE) }
            exception.message shouldBe "이미 존재하는 카드를 추가할 수 없다."
        }

        test("카드를 deal할 수 있다.") {
            val cards = Cards()
            cards.deal(SPADE_TWO)
            cards.values() shouldHaveSize 1
        }
    }

    context("hit") {
        test("2장미만의 카드가 있는데 hit하는 경우 예외가 발생한다") {
            val cards = Cards.of(SPADE_ACE)
            val exception = shouldThrowExactly<IllegalStateException> { cards.hit(SPADE_TWO) }
            exception.message shouldBe "카드가 deal이 완료되지 않아 hit할 수 없다."
        }

        test("최소 계산된 점수가 21점 초과되는데 hit하는 경우 예외가 발생한다") {
            val cards = Cards.of(SPADE_KING, SPADE_QUEEN, SPADE_JACK)
            val exception = shouldThrowExactly<IllegalStateException> { cards.hit(SPADE_ACE) }
            exception.message shouldBe "카드가 bust되어 더이상 hit할 수 없다."
        }

        test("중복된 카드를 hit하는 경우 예외가 발생한다.") {
            val cards = Cards.of(SPADE_ACE, SPADE_TWO)
            val exception = shouldThrowExactly<IllegalArgumentException> { cards.hit(SPADE_ACE) }
            exception.message shouldBe "이미 존재하는 카드를 추가할 수 없다."
        }

        test("카드를 hit 할 수 있다.") {
            val cards = Cards.of(SPADE_ACE, SPADE_KING, SPADE_JACK)
            cards.hit(SPADE_QUEEN)
            cards.values() shouldHaveSize 4
        }
    }

    context("of") {
        test("card를 받아 생성한다.") {
            val actual = Cards.of(SPADE_ACE, SPADE_KING)
            actual.values() shouldHaveSize 2
        }
    }
})
