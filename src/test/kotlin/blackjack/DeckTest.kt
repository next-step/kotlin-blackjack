package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DeckTest : BehaviorSpec({
    given("블랙잭에서는") {
        `when`("최초로 게임을 시작했을 때") {
            val deck = Deck()
            then("52장의 카드 덱을 가진다.") {
                deck.cards.values.size shouldBe 52
            }

            then("중복없는 플레잉 카드를 가진다.") {
                deck.cards.values shouldBe Card.DECK
            }
        }
    }
})
