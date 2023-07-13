package blackjack.domain

import blackjack.fixture.PlayerFixture.DEALER_NAME
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_BET_AMOUNT
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.doubles.shouldBeZero
import io.kotest.matchers.shouldBe

class GameProfitTest : FunSpec({

    test("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.") {
        // given
        val players = Players(
            Player(
                name = PLAYER_SONG2_NAME,
                cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART), Card(Rank.JACK, Suit.HEART)),
                betAmount = PLAYER_SONG2_BET_AMOUNT
            )
        )
        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART), Card(Rank.JACK, Suit.HEART))
        )

        // when
        val actual = GameProfit(players, dealer).profitOfParticipants()

        // then
        actual.forAll {
            if (it.participantName == "dealer") {
                it.profit.shouldBeZero()
            } else {
                it.profit shouldBe PLAYER_SONG2_BET_AMOUNT
            }
        }
    }
})
