package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : StringSpec({

    "쉼표로 참여자를 구분한다" {
        val participant = Participant("a,b,c")
        participant.getParticipantNames() shouldBe listOf("a", "b", "c")
    }
})
