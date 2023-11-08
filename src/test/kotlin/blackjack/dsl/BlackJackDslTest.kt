package blackjack.dsl

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.dsl.BlackJackDsl.initTrumpCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackDslTest : BehaviorSpec({
    given("블랙잭 DSL을 사용하면") {
        `when`("카드를 생성하면") {
            val trumpCard = initTrumpCard {
                card(Card(Suit.SPADE, Rank.ACE))
                card(Card(Suit.HEART, Rank.TWO))
            }
            then("카드는 2장이 된다.") {
                trumpCard.cards.size shouldBe 2
            }
        }
    }
})
