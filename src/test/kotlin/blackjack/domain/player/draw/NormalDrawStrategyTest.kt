package blackjack.domain.player.draw

import blackjack.domain.card.CardDeck
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NormalDrawStrategyTest : StringSpec({
    "카드를 뽑는다" {
        forAll(
            row(1),
            row(2),
            row(3)
        ) {
            val normalDrawStrategy = NormalDrawStrategy()
            val deck = CardDeck()
            val result = normalDrawStrategy.draw(deck, it)

            result.size shouldBe it
        }
    }
})
