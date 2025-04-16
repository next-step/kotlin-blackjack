package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.CardFixture.SPADES_SEVEN
import blackjack.domain.card.CardFixture.SPADES_SIX
import blackjack.domain.card.CardFixture.SPADES_TWO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HitTest : FunSpec({
    context("addCard") {
        test("should remain Hit until bust") {
            val hit =
                Hit(
                    Hands(
                        listOf(
                            SPADES_SIX,
                            SPADES_SEVEN,
                        ),
                    ),
                )
            val state = hit.addCard(SPADES_TWO)

            state::class shouldBe Hit::class
        }
    }
})
