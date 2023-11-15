package blackjack.domain.player

import blackjack.domain.player.PlayerName
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerNameTest : StringSpec({
    "문자열로 플레이어 이름 생성" {
        val name = "홍길동"

        val result = PlayerName(name)

        result.value shouldBe name
    }

    "공백인 이름은 불가" {
        listOf("", "  ").forAll { blankName ->
            shouldThrowExactly<IllegalArgumentException> {
                PlayerName(blankName)
            }
        }
    }
})
