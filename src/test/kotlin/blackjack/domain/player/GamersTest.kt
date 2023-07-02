package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class GamersTest : StringSpec({

    "중복된 Gamer가 있을 때, 예외가 발생한다." {
        val gamer1 = Gamer("gray")
        val gamer2 = Gamer("gray")

        shouldThrow<IllegalArgumentException> {
            Gamers(listOf(gamer1, gamer2))
        }
    }
})
