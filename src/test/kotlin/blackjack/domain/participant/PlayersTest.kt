package blackjack.domain.participant

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class PlayersTest : FreeSpec({

    "이름 값을 입력할 때" - {
        val dealer = Dealer()

        listOf(
            listOf("호랑이"),
            listOf("현구님", "규남님")
        ).forEach { nameValues ->
            "$nameValues 가 입력되면 플레이어 목록이 잘 생성된다." {
                val players = Players.of(playNameValues = nameValues, dealer)
                players.values.map { it.getPlayerNameValue() } shouldContainExactly nameValues
            }
        }

        "빈 리스트가 입력되면 플레이어 목록 생성에 실패한다." {
            val exception =
                shouldThrowExactly<IllegalArgumentException> { Players.of(playNameValues = emptyList(), dealer) }
            exception.message shouldBe "플레이어가 없으면 게임을 진행할 수 없습니다."
        }
    }

    "딜러를 통해 초기 시작 카드를 2장씩 나눠 받는다." {
        // given
        val dealer = Dealer()
        val players = Players.of(listOf("현구님", "규남님"), dealer)

        // then
        players.values.forEach { it.cards().shouldHaveSize(2) }
    }
})
