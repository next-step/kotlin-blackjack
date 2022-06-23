package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class PlayerNameTest : FreeSpec({

    "사용자 이름은 공백으로 이루어질 수 없다." - {
        listOf(
            "",
            " ",
            "  "
        ).forAll { value ->
            val exception = shouldThrowExactly<IllegalArgumentException> { PlayerName(value = value) }
            exception.message shouldBe "이름은 공백으로 이루어질 수 없습니다."
        }
    }
})
