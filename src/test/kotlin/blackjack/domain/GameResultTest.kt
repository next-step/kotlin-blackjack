package blackjack.domain

import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameResultTest : FunSpec({

    test("플레이어가 가진 카드 리스트의 총 점수를 계산한다.") {
        // given
        val cards = Cards(Card(Rank.QUEEN), Card(Rank.ACE), Card(Rank.KING))
        val player = Player(PLAYER_SONG2_NAME, cards)
        val players = listOf(player)
        val gameResult = GameResult(players)

        // when
        val actual = gameResult.scoreMap

        // then
        actual[player] shouldBe 21
    }
})
