package blackjack.domain.game

import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class BlackJackGameTest : DescribeSpec({

    describe(name = "블랙잭 게임에 플레이어 이름을 제공해서 게임을 시작할 수 있다.") {
        val playerNames = listOf("진원", "패자")
        val blackJackGame = BlackJackGame(playerNames = playerNames)

        context(name = "사용자의 이름이 정해진 범위에 입력하지 않으면") {
            val expect = "안녕하세요이름초과예요"

            val exception = shouldThrow<IllegalArgumentException> {
                BlackJackGame(playerNames = listOf("진원", expect))
            }

            it(name = "정해진 범위에 입력하지 않았다는 에러 메시지를 반환한다.") {
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                    arrayOf(1..10, expect)
                )
            }
        }
    }
})
