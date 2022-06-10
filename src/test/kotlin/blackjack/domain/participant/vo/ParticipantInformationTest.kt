package blackjack.domain.participant.vo

import blackjack.domain.Score
import blackjack.domain.participant.type.Status
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantInformationTest : StringSpec({
    "참가자 정보를 생성할수 있다." {
        shouldNotThrow<Throwable> { ParticipantInformation(Name("dean"), Status.PLAY) }
    }

    "현재 상태값을 조회할수 있다." {
        val participantInformation = ParticipantInformation(Name("dean"), Status.PLAY)

        participantInformation.isBust() shouldBe false
        participantInformation.isStay() shouldBe false
        participantInformation.isPlay() shouldBe true
    }

    "점수가 21점일 경우 BLACKJACK 상태를 생성한다." {
        val participantInformation = ParticipantInformation(Name("dean"), Status.PLAY)

        val blackJack = participantInformation.changeStatus(Score.BLACKJACK)

        blackJack.status shouldBe Status.BLACKJACK
    }

    "점수가 21점을 넘을 경우 BUST 상태를 생성한다." {
        val participantInformation = ParticipantInformation(Name("dean"), Status.PLAY)

        val bust = participantInformation.changeStatus(Score.of(22))

        bust.status shouldBe Status.BUST
    }

    "점수가 21점 미만 경우 PLAY 생태를 생성한다." {
        val participantInformation = ParticipantInformation(Name("dean"), Status.PLAY)

        val play = participantInformation.changeStatus(Score.of(20))

        play.status shouldBe Status.PLAY
    }

    "STAY 상태를 제공할수 있다." {
        val participantInformation = ParticipantInformation(Name("dean"), Status.PLAY)

        val stay = participantInformation.stay()

        stay.status shouldBe Status.STAY
    }
})
