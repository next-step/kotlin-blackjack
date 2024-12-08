package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.InitialCardsTestFixtures.initial20Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class StayTest : BehaviorSpec({
    given("20 Stay 상태면") {
        val initialCards = Cards(initial20Cards)
        val sut = Stay(initialCards)

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
