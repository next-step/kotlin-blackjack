package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardTest : BehaviorSpec({

    given("카드에 모양과 숫자가 주어졌을때") {
        val suit = Suit.SPADE
        val rank = Rank.ACE
        `when`("카드 생성을 하면") {
            val card = Card(suit, rank)
            then("모양은 스페이드 숫자는 ACE가 된다.") {
                card.suit shouldBe Suit.SPADE
                card.rank shouldBe Rank.ACE
            }
        }
    }

    given("카드의 숫자가 ACE 주어졌을때") {
        val card = Card(Suit.SPADE, Rank.ACE)
        `when`("카드의 점수를 계산하면") {
            val score = card.calculateScore()
            then("카드의 점수는 1 또는 11이 된다.") {
                score.first shouldBe 1
                score.second shouldBe 11
            }
        }
    }

    given("카드에 숫자가 2가 아닌게 주어졌을때") {
        val card = Card(Suit.SPADE, Rank.TWO)
        `when`("카드의 점수를 계산하면") {
            val score = card.calculateScore()
            then("카드의 점수는 2가 된다.") {
                score.first shouldBe 2
                score.second shouldBe 2
            }
        }
    }
})
