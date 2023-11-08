package blackjack.dsl

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.dsl.BlackJackDsl.initTrumpCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackDslTest : BehaviorSpec({
    given("블랙잭 DSL을 사용하면") {
        `when`("트럼프 카드를 생성하는데 52장이 아니면") {
            then("예외가 발생한다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    initTrumpCard {
                        card(Card(Suit.SPADE, Rank.ACE))
                    }
                }
                exception.message shouldBe "카드는 52장이어야 합니다."
            }
        }
    }
})
