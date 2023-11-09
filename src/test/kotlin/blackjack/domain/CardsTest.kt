package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardsTest : BehaviorSpec({

    given("카드 스페이드A 하트9를 받았다면") {
        val cards = Cards().apply {
            add(Card(Suit.SPADE, Rank.ACE))
            add(Card(Suit.HEART, Rank.NINE))
        }
        `when`("카드 점수를 계산하면") {
            val score = cards.score()
            then("점수는 20점이다.") {
                score shouldBe Score(20)
            }
        }
    }

    given("카드 스페이드A 하트9 다이아몬드 킹을 받았다면") {
        val cards = Cards().apply {
            add(Card(Suit.SPADE, Rank.ACE))
            add(Card(Suit.HEART, Rank.NINE))
            add(Card(Suit.DIAMOND, Rank.KING))
        }
        `when`("점수를 계산하면") {
            val score = cards.score()
            then("점수는 20점이다.") {
                score shouldBe Score(20)
            }
        }
    }
})
