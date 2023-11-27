package blackjack.domain.component

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row

class BlackjackInputValidatorTest : FunSpec({
    val validator = BlackjackInputValidator()

    test("플레이어 이름이 null 또는 공백인 경우 IllegalArgumentException 발생 테스트") {
        forAll(
            row(null),
            row(""),
            row(" "),
        ) { playerNamesString ->
            shouldThrow<IllegalArgumentException> { validator.validatePlayerNamesString(playerNamesString) }
        }
    }

    test("플레이어 이름 목록이 존재하지 않을 경우 IllegalArgumentException 테스트") {
        val input = listOf<String>()

        shouldThrow<IllegalArgumentException> { validator.validatePlayerNamesSize(input) }
    }
})
