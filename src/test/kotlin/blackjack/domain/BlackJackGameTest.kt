package blackjack.domain

import blackjack.fixture.PlayerFixture.PLAYER_SONG2
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : FunSpec({

    test("각 플레이어가 가지고 있는 카드의 계산 결과를 반환한다.") {
        // given
        val cards = Cards(Card(Rank.ACE))
        val player = Player(PLAYER_SONG2_NAME, cards)
        val game = BlackJackGame(players = Players(player))
        val expected = 11

        // when
        val actual = game.getGameResult()

        // then
        actual[player] shouldBe expected
    }
})
