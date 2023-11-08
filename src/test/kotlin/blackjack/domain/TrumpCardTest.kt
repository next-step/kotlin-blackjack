package blackjack.domain

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

    given("트럼프 카드를 2장을 생성하고") {
        val trumpCard = TrumpCard(
            mutableListOf(
                Card(Suit.SPADE, Rank.ACE),
                Card(Suit.HEART, Rank.TWO)
            )
        )
        `when`("카드를 한장 뽑으면") {
            val card = trumpCard.draw()
            then("뽑은 카드는 스페이드 A이다.") {
                card shouldBe Card(Suit.SPADE, Rank.ACE)
            }
            then("남은 트럼프 카드는 1장이다.") {
                trumpCard.cards.size shouldBe 1
            }
        }
    }
})
