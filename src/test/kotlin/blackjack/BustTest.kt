package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeThreeCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BustTest : BehaviorSpec({
    given("22 Bust 상태면") {
        val initialCards =
            Cards(
                listOf(
                    spadeKingCard,
                    spadeNineCard,
                    spadeThreeCard,
                ),
            )
        val sut = Bust(initialCards)

        `when`("draw(A)를 호출하면") {
            then("IllegalStateException 을 반환한다") {
                shouldThrow<IllegalStateException> {
                    val newCard: Card = spadeAceCard
                    sut.draw(newCard)
                }
            }
        }

        `when`("stay()를 호출하면") {
            val result = sut.stay()
            then("자기 자신을 반환한다") {
                result shouldBe sut
            }
        }

        `when`("isFinished()를 호출하면") {
            val result = sut.isFinished()
            then("true를 반환한다") {
                result shouldBe true
            }
        }
    }
})
