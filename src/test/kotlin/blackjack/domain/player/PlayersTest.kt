package blackjack.domain.player

import blackjack.domain.model.BlackjackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayersTest : StringSpec({

    "플레이어를 생성할 때 정해진 범위의 플레이어 수가 아니라면 참석 가능한 플레이어 범위가 아니라는 에러가 발생한다." {
        forAll(
            row(mockPlayers("진원"), 1),
            row(mockPlayers("진원", "포비", "태양", "천왕성", "해왕성", "목성", "토성", "세종", "태종"), 9),
        ) { players, playerCount ->
            val exception = shouldThrow<IllegalStateException> {
                Players(players = players)
            }

            exception shouldHaveMessage BlackjackErrorCode.CAN_NOT_PARTICIPATE_RANGE_OF_PLAYERS.message(
                arrayOf(2..8, playerCount)
            )
        }
    }

    "플레이어를 생성할 때 정해진 범위의 플레이어 수라면 정상적으로 생성된다." {
        forAll(
            row(mockPlayers("진원", "태양")),
            row(mockPlayers("천왕성", "해왕성", "목성", "토성", "세종", "태종")),
        ) { players ->
            val createdPlayers = Players(players = players)

            createdPlayers shouldBe players
        }
    }
})
