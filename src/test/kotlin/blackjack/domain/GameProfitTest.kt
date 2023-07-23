package blackjack.domain

import blackjack.fixture.PlayerFixture.DEALER_NAME
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_BET_AMOUNT
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.doubles.shouldBeZero
import io.kotest.matchers.shouldBe

class GameProfitTest : FunSpec({

    test("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.") {
        // given
        val players = Players(
            Player(
                name = PLAYER_SONG2_NAME,
                cards = Cards(
                    Card(Rank.ACE, Suit.SPADE),
                    Card(Rank.KING, Suit.HEART),
                    Card(Rank.JACK, Suit.HEART)
                ),
                betAmount = PLAYER_SONG2_BET_AMOUNT
            )
        )
        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.JACK, Suit.HEART)
            )
        )
        val expected = PLAYER_SONG2_BET_AMOUNT * 1.5

        // when
        val actual = GameProfit(players, dealer).profitOfParticipants()

        // then
        actual.forAll {
            if (it.participantName == "dealer") {
                it.profit shouldBe -expected
            } else {
                it.profit shouldBe expected
            }
        }
    }

    test("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.") {
        // given
        val players = Players(
            Player(
                name = PLAYER_SONG2_NAME,
                cards = Cards(
                    Card(Rank.ACE, Suit.SPADE),
                    Card(Rank.KING, Suit.HEART),
                    Card(Rank.JACK, Suit.HEART)
                ),
                betAmount = PLAYER_SONG2_BET_AMOUNT
            )
        )
        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.JACK, Suit.HEART)
            )
        )

        // when
        val actual = GameProfit(players, dealer).profitOfParticipants()

        // then
        actual.forAll {
            if (it.participantName == "dealer") {
                it.profit.shouldBeZero()
            } else {
                it.profit.shouldBeZero()
            }
        }
    }

    test("플레이어가 이기면 배팅 금액만큼 받고 딜러는 배팅 금액만큼 잃는다.") {
        // given
        val players = Players(
            Player(
                name = PLAYER_SONG2_NAME,
                cards = Cards(
                    Card(Rank.ACE, Suit.SPADE),
                    Card(Rank.TWO, Suit.HEART),
                    Card(Rank.JACK, Suit.HEART)
                ),
                betAmount = PLAYER_SONG2_BET_AMOUNT
            )
        )
        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART)
            )
        )

        // when
        val actual = GameProfit(players, dealer).profitOfParticipants()

        // then
        actual.forAll {
            if (it.participantName == "dealer") {
                it.profit shouldBe -PLAYER_SONG2_BET_AMOUNT
            } else {
                it.profit shouldBe PLAYER_SONG2_BET_AMOUNT
            }
        }
    }

    test("플레이어가 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 잃었을 경우 딜러는 배팅금액만큼 받는다.") {
        // given
        val players = Players(
            Player(
                name = PLAYER_SONG2_NAME,
                cards = Cards(
                    Card(Rank.QUEEN, Suit.SPADE),
                    Card(Rank.KING, Suit.HEART),
                    Card(Rank.JACK, Suit.HEART)
                ),
                betAmount = PLAYER_SONG2_BET_AMOUNT
            )
        )
        val dealer = Dealer(
            name = DEALER_NAME,
            cards = Cards(Card(Rank.TWO, Suit.SPADE), Card(Rank.THREE, Suit.HEART))
        )

        // when
        val actual = GameProfit(players, dealer).profitOfParticipants()

        // then
        actual.forAll {
            if (it.participantName == "dealer") {
                it.profit shouldBe PLAYER_SONG2_BET_AMOUNT
            } else {
                it.profit shouldBe -PLAYER_SONG2_BET_AMOUNT
            }
        }
    }
})
