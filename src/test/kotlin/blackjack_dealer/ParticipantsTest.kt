package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : StringSpec({
    "전달한 이름의 수만큼 참가자를 생성했다" {
        val input = "pita,haero,sery"
        val expected = 3
        CardDeque.create()
        Participants.newInstance(input).size shouldBe expected
    }
})
