package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class TrumpCardTest : BehaviorSpec({
    given("트럼프 카드를 생성한다.") {
        `when`("트럼프 카드를 생성하면") {
            val trumpCard = TrumpCard.init()
            then("트럼프 카드는 52장이다.") {
                trumpCard.cards.size shouldBe 52
            }
        }
    }

    given("트럼프 카드를 생성할때 52장 이하이면") {
        val cards = listOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.HEART, Rank.TWO))
        `when`("트럼프 카드를 생성하면") {
            then("에러가 발생한다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    TrumpCard(cards)
                }
                exception.message shouldBe "카드는 52장이어야 합니다."
            }
        }
    }
})
