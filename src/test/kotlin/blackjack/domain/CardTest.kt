package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardTest : BehaviorSpec({

    given("카드에 스페이드 모양과 숫자 ACE가 주어졌을때") {
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
})
