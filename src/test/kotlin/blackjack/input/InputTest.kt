package blackjack.input

import blackjack.ui.InputView
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class InputTest : StringSpec({
    "구분자인 ,가 아닌 다른 문자로 구분했을 때" {
        val input = "pita;haero,dol"
        shouldThrow<IllegalStateException> {
            InputView.checkParticipants(input)
        }
    }

    "참가자가 ,로 시작하였을 때" {
        val input = ",pita,dol"
        shouldThrow<IllegalStateException> {
            InputView.checkParticipants(input)
        }
    }

    "참가자가 ,로 끝났을 때" {
        val input = "haero,dol,"
        shouldThrow<IllegalStateException> {
            InputView.checkParticipants(input)
        }
    }
})
