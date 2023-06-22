package blackjack

import blackjack.CardTest.Companion.SPADE_ACE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class CardDeckTest : FunSpec({

    context("deal") {
        test("카드를 deal할 때 보유중인 카드가 더 없는 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalStateException> { CardDeck().deal() }
            exception.message shouldBe "남은 카드가 없을 땐 deal할 수 없다"
        }

        test("카드를 1장 deal한다.") {
            val cardDeck = CardDeck(LinkedList(listOf(SPADE_ACE)))
            val actual = cardDeck.deal()
            actual shouldBe SPADE_ACE
        }
    }

    context("randomCardDeck") {
        test("랜덤한 카드 52장을 받을 수 있다.") {
            val actual = CardDeck.randomCardDeck()
            actual.size() shouldBe 52
        }
    }
})
