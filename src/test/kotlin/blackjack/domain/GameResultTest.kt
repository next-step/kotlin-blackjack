package blackjack.domain

import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameResultTest : FunSpec({

    test("플레이어가 가진 카드 리스트의 총 점수를 계산한다.") {
        // given
        val cards = Cards(Card(Rank.QUEEN, Suit.SPADE), Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        val player = Player(PLAYER_SONG2_NAME, cards)
        val gameResult = GameResult(Players(player))

        // when
        val actual = gameResult.scoreMap

        // then
        actual[player] shouldBe 21
    }

    test("Ace는 21을 넘지 않으면서 가장 가깝도록 1 또는 11을 선택한다.") {
        // given
        val cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Player(PLAYER_SONG2_NAME, cards)
        val gameResult = GameResult(Players(player))
        val expected = 12

        // when
        val actual = gameResult.scoreMap

        // then
        actual[player] shouldBe expected
    }
})
