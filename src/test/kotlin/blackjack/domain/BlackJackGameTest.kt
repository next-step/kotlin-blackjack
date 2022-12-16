package blackjack.domain

import blackjack.view.InputView
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "플레이어가 없으면 에러 발생" {
        // given
        val players = emptyList<Player>()
        // when
        val exception = shouldThrowExactly<IllegalArgumentException> {
            BlackJackGame(InputView(), CardManager(CardGenerator()), players)
        }
        // then
        exception.message shouldBe "플레이어는 최소 2명 이상 필요합니다."
    }

})
