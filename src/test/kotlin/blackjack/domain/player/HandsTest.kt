package blackjack.domain.player

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardFixture.SPADES_JACK
import blackjack.domain.card.CardFixture.SPADES_QUEEN
import blackjack.domain.card.CardFixture.SPADES_SEVEN
import blackjack.domain.card.CardFixture.SPADES_SIX
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
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

    context("score") {
        test("score is calculated correctly") {
            this@context.withData(
                listOf(SPADES_JACK, SPADES_SIX) to 16,
                listOf(SPADES_SIX, SPADES_SEVEN) to 13,
                listOf(SPADES_QUEEN, SPADES_JACK) to 20,
            ) { (cards, expected) ->
                val hands = Hands(cards)

                hands.calculateScore() shouldBe expected
            }
        }

        test("ace is counted as 1 if cards + ACE(11) is over than 21") {
            val cards = listOf(SPADES_QUEEN, SPADES_JACK, SPADES_ACE)
            val hands = Hands(cards)

            hands.calculateScore() shouldBe 21
        }

        test("ace is counted as 11 if cards + ACE(11) is below or equal to 21") {
            val cards = listOf(SPADES_QUEEN, SPADES_ACE)
            val hands = Hands(cards)

            hands.calculateScore() shouldBe 21
        }
    }
})
