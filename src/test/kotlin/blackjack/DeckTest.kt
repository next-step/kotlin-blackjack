package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DeckTest : BehaviorSpec({
    given("블랙잭에서는") {
        `when`("최초로 게임을 시작했을 때") {
            val deck = Deck()
            then("52장의 카드 덱을 가진다.") {
                deck.cards.size shouldBe 52
            }
        }
    }

    given("게임을 최초로 시작했을 때 또는 게임 중간에") {
        val deck = Deck()
        `when`("플레이어에게 한 장을 주면") {
            deck.pick()
            then("덱의 카드 수가 1 줄어든다.") {
                deck.cards.size shouldBe 51
            }
        }
    }
})
