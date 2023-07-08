package blackjack.domain

import blackjack.fixture.PlayerFixture.DEALER_NAME
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameResultTest : FunSpec({

    test("플레이어별로 승패를 판단할 수 있다.") {
        // given
        val player =
            Player(name = PLAYER_SONG2_NAME, cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLUB)))
        val dealer = Dealer(name = DEALER_NAME, cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.TWO, Suit.HEART)))
        val expected = GameResult.WIN

        // when
        val actual = GameResult.resultOfPlayer(player, dealer)

        // then
        actual shouldBe expected
    }

    test("딜러가 21을 초과하면 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.") {
        // given
        val player =
            Player(name = PLAYER_SONG2_NAME, cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLUB)))
        val dealer = Dealer(
            name = DEALER_NAME, cards = Cards(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.QUEEN, Suit.HEART)
            )
        )
        val expected = GameResult.WIN

        // when
        val actual = GameResult.resultOfPlayer(player, dealer)

        // then
        actual shouldBe expected
    }
})
