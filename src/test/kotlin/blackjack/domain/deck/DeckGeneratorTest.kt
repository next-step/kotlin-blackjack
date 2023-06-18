package blackjack.domain.deck

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

class DeckGeneratorTest : StringSpec({

    "덱 생성기에서 게임에서 사용할 덱을 가져올 수 있으며 Denomination * Suit 만큼의 개수를 가진다." {
        val generator = DeckGenerator.generator()

        generator shouldHaveSize Denomination.values().size * Suit.values().size
    }
})
