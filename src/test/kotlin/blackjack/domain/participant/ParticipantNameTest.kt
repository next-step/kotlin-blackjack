package blackjack.domain.participant

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec

class ParticipantNameTest : StringSpec({

    "플레이어의 이름 길이가 1글자 미만이면 예외가 발생한다." {
        // given
        val name = ""

        // expected
        shouldThrowWithMessage<IllegalArgumentException>("이름은 최소 1글자 이상이어야 합니다.") {
            ParticipantName(name)
        }
    }
})
