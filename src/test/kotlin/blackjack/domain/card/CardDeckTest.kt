package blackjack.domain.card

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class CardDeckTest : StringSpec({
    "카드 덱을 생성할수 있다." {
        shouldNotThrow<Throwable> {
            setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
        }
    }
})
