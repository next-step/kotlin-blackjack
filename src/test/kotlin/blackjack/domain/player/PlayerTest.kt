package blackjack.domain.player

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class PlayerTest : StringSpec({
    "참가자 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Player.sit(Name("dean")) }
    }
})
