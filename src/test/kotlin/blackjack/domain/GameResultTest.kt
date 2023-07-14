package blackjack.domain

import blackjack.fixture.PlayerFixture.DEALER_NAME
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_BET_AMOUNT
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameResultTest : FunSpec({

    test("플레이어별로 승패를 판단할 수 있다.") {
        // given
        val player = Player(
            name = PLAYER_SONG2_NAME,
            cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLUB)),
            betAmount = PLAYER_SONG2_BET_AMOUNT
        )
        val dealer = Dealer(name = DEALER_NAME, cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.TWO, Suit.HEART)))
        val expected = GameResult.WIN

        // when
        val actual = GameResult.resultOfPlayer(player, dealer)

        // then
        actual shouldBe expected
    }

    test("딜러가 21을 초과하면 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.") {
        // given
        val player = Player(
            name = PLAYER_SONG2_NAME,
            cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLUB)),
            betAmount = PLAYER_SONG2_BET_AMOUNT
        )

        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
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

    test("딜러가 21을 넘지 않고 플레이어가 21을 초과하면 딜러가 승리한다.") {
        // given
        val player = Player(
            name = PLAYER_SONG2_NAME,
            cards = Cards(
                Card(Rank.QUEEN, Suit.SPADE),
                Card(Rank.KING, Suit.CLUB),
                Card(Rank.NINE, Suit.HEART)
            ),
            betAmount = PLAYER_SONG2_BET_AMOUNT
        )

        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.QUEEN, Suit.HEART)
            )
        )

        // when
        val actual = GameResult.resultOfPlayer(player, dealer)

        // then
        actual shouldBe GameResult.LOSE
    }

    test("딜러와 플레이어가 모두 동시에 블랙잭인 경우 무승부이다.") {
        // given
        val player = Player(
            name = PLAYER_SONG2_NAME,
            cards = Cards(
                Card(Rank.QUEEN, Suit.SPADE),
                Card(Rank.KING, Suit.CLUB),
                Card(Rank.ACE, Suit.HEART)
            ),
            betAmount = PLAYER_SONG2_BET_AMOUNT
        )

        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
                Card(Rank.QUEEN, Suit.SPADE),
                Card(Rank.KING, Suit.CLUB),
                Card(Rank.ACE, Suit.HEART)
            )
        )

        // when
        val actual = GameResult.resultOfPlayer(player, dealer)

        // then
        actual shouldBe GameResult.DRAW
    }
})
