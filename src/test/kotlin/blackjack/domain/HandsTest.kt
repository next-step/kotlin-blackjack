package blackjack.domain

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardFixture.SPADES_JACK
import blackjack.domain.card.CardFixture.SPADES_QUEEN
import blackjack.domain.card.CardFixture.SPADES_SIX
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HandsTest : FunSpec({
    context("initialized") {
        test("if there are two cards should return true") {
            val hands =
                Hands(
                    listOf(
                        SPADES_JACK,
                        SPADES_SIX,
                    ),
                )

            hands.initialized shouldBe true
        }

        test("if there are less than two cards should return false") {
            val hands =
                Hands(
                    listOf(SPADES_ACE),
                )

            hands.initialized shouldBe false
        }
    }

    test("size") {
        val hands = Hands()

        hands.size shouldBe 0
    }

    test("addCard") {
        val hands = Hands()
        val actual = hands + SPADES_JACK

        actual.size shouldBe 1
    }

    test("bust") {
        val hands =
            Hands(
                listOf(
                    SPADES_JACK,
                    SPADES_SIX,
                    SPADES_QUEEN,
                ),
            )

        hands.isBust() shouldBe true
    }
})
