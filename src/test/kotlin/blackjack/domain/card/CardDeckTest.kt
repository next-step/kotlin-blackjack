package blackjack.domain.card

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
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

    "중복되는 카드가 존재하면 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> {
            setupCardDeck {
                spade()
                diamond()
                heart()
                heart()
                heart()
            }
        }
    }
})
