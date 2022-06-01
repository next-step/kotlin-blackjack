package blackjack.domain.player

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class CardsInHandTest : StringSpec({
    "손에 든 카드를 생성할수 있다." {
        shouldNotThrow<Throwable> { CardsInHand(emptyList()) }
    }
})
