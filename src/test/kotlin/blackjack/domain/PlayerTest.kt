package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class PlayerTest : StringSpec({
    "플레이어의 이름이 빈칸 혹은 공백이면 예외를 발생한다." {
        listOf(
            " ",
            ""
        ).forEach {
            // when //then
            shouldThrowExactly<IllegalArgumentException> { Player(it) }
        }
    }
})
