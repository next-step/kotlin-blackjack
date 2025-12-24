package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : FreeSpec({

    "참가자 간 이름 중복 검사" {
        val exception =
            shouldThrow<IllegalArgumentException> {
                Players(
                    listOf(
                        Player(name = "juri", bettingAmount = 10_000L),
                        Player(name = "juri", bettingAmount = 10_000L),
                        Player(name = "ryeol", bettingAmount = 10_000L),
                    ),
                )
            }
        exception.message shouldBe "참가자의 이름이 중복될 수 없습니다."
    }
    "참가자 목록 정상 생성" {
        val players =
            Players(
                listOf(
                    Player(name = "juri", bettingAmount = 10_000L),
                    Player(name = "yebin", bettingAmount = 10_000L),
                    Player(name = "ryeol", bettingAmount = 10_000L),
                ),
            )

        players.players.size shouldBe 3
    }
})
