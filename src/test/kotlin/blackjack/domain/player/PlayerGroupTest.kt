package blackjack.domain.player

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayerGroupTest {
    @Test
    fun `블랙잭 게임의 최대 인원은 8명이다`() {
        val players = listOf(
            player { name("pobi") },
            player { name("hojun") },
            player { name("jason") },
            player { name("jack") },
            player { name("max") },
            player { name("john") },
            player { name("harry") },
            player { name("honux") },
        )
        shouldNotThrowAny { PlayerGroup(players) }
    }

    @Test
    fun `블랙잭 참가 인원이 8명을 초과하면 예외가 발생한다`() {
        val players = listOf(
            player { name("pobi") },
            player { name("hojun") },
            player { name("jason") },
            player { name("jack") },
            player { name("max") },
            player { name("john") },
            player { name("harry") },
            player { name("honux") },
            player { name("crong") },
        )
        shouldThrow<IllegalArgumentException> { PlayerGroup(players) }
    }
}
