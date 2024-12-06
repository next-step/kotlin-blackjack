package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerNameTest : StringSpec({

    "PlayerName 객체를 생성할 수 있다" {
        // Arrange:
        val playerName = PlayerName("test")

        // Act:
        val result = playerName

        // Assert:
        result shouldBe PlayerName("test")
    }

    "PlayerName 객체는 공백이면 예외가 발생한다." {
        // Arrange:
        val value = ""

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            PlayerName(value)
        }

        // Assert:
        result.message shouldBe "이름은 공백일 수 없습니다. input = "
    }
})
