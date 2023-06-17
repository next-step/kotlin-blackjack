package blackjack.domain.player

import blackjack.domain.behavior.mockState
import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : StringSpec({

    "플레이어 이름이 정해진 범위가 아니라면 정해진 범위가 아니라는 에러 메시지를 반환한다." {
        forAll(
            row("안녕하세요이름초과예요"),
            row(""),
            row("2"),
        ) { name ->
            val exception = shouldThrow<IllegalArgumentException> {
                Player(name = name, state = mockState)
            }

            exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                arrayOf(1..10, name.trim())
            )
        }
    }

    "플레이어 이름이 정해진 범위라면 플레이어가 정상적으로 생성된다." {
        forAll(
            row("진원"),
            row("포비"),
        ) { name ->
            val player = Player(name = name, state = mockState)

            player.name shouldBe name
        }
    }
})
