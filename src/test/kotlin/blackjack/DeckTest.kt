package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DeckTest : BehaviorSpec({
    given("블랙잭에서는") {
        `when`("최초로 게임을 시작했을 때") {
            val initDeck = Deck()
            then("52장의 카드 덱을 가진다.") {
                initDeck.cards.size shouldBe 52
            }
        }
    }
})
