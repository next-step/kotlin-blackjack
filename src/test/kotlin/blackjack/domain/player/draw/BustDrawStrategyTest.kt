package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BustDrawStrategyTest : StringSpec({
    "버스트 상태에서는 카드를 뽑을 수 없다" {
        val bustDrawStrategy = BustDrawStrategy()
        shouldThrow<IllegalStateException> {
            bustDrawStrategy.draw(CardDeck(), count = 1)
        }
    }
})
