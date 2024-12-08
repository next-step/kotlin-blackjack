package blackjack

import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.InitialCardsTestFixtures.initial16Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.types.shouldBeInstanceOf

class HitTest : BehaviorSpec({
    given("16 Hit 상태에서") {
        val initialCards = Cards(initial16Cards)
        val sut = Hit(initialCards)

        `when`("stay()를 호출하면") {
            val result: State = sut.stay()
            then("Stay 상태를 반환한다") {
                result.shouldBeInstanceOf<Stay>()
            }
        }
        `when`("5카드를 뽑으면") {
            val card = spadeFiveCard
            val result: State = sut.draw(card)
            then("Running 상태를 반환한다") {
                result.shouldBeInstanceOf<Running>()
            }
        }
        `when`("6카드를 뽑으면") {
            val card = spadeSixCard
            val result: State = sut.draw(card)
            then("Bust 상태를 반환한다") {
                result.shouldBeInstanceOf<Bust>()
            }
        }
        `when`("isFinished()를 호출하면") {
            val result = sut.isFinished()
            then("false 를 반환한다") {
                result.shouldBeFalse()
            }
        }
    }
})
