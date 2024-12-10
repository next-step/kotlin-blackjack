package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : StringSpec({
    "이름으로 참가자를 조회한다." {
        val sut = Participants(
            listOf(
                Player(participantName = ParticipantName("테스트1")),
                Player(participantName = ParticipantName("테스트2")),
            )
        )

        val actual = sut.findByName(ParticipantName("테스트1"))

        actual?.name shouldBe ParticipantName("테스트1")
    }

    "존재하지 않는 참가자를 조회하면 null을 반환한다." {
        val sut = Participants(
            listOf(
                Player(participantName = ParticipantName("테스트1")),
                Player(participantName = ParticipantName("테스트2")),
            )
        )

        val actual = sut.findByName(ParticipantName("테스트3"))

        actual?.name shouldBe null
    }

    "카드를 뽑을 플레이어를 반환한다." {
        val sut = Participants(
            listOf(Player(participantName = ParticipantName("테스트1")), Player(participantName = ParticipantName("테스트2")))
        )

        val playerName1 = sut.findDrawPlayer()
        playerName1?.name shouldBe ParticipantName("테스트1")

        val playerName2 = sut.findDrawPlayer()
        playerName2?.name shouldBe ParticipantName("테스트2")
    }

    "더이상 카드를 뽑지 않는 플레이어 존재하면 다음 플레이어를 반환한다." {
        val sut = Participants(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                    draw = false,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                ),
            )
        )
        val actual = sut.findDrawPlayer()
        actual?.name shouldBe ParticipantName("테스트2")
    }

    "하나의 플레이어가 카드를 계속 뽑는다면 true를 반환한다." {
        val sut = Participants(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.canDrawForAllPlayers()
        actual shouldBe true
    }

    "모든 플레이어가 더이상 카드를 뽑지 않는 경우 false를 반환한다." {
        val sut = Participants(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                    draw = false,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.canDrawForAllPlayers()
        actual shouldBe false
    }

    "하나의 플레이어가 카드를 계속 뽑는다면 false를 반환한다." {
        val sut = Participants(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.isAllPlayerStopDraw()
        actual shouldBe false
    }

    "모든 플레이어가 더이상 카드를 뽑지 않는 경우 true를 반환한다." {
        val sut = Participants(
            listOf(
                Player(
                    participantName = ParticipantName("테스트1"),
                    draw = false,
                ),
                Player(
                    participantName = ParticipantName("테스트2"),
                    draw = false,
                ),
            )
        )
        val actual = sut.isAllPlayerStopDraw()
        actual shouldBe true
    }
})
