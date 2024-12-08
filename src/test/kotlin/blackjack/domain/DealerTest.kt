package blackjack.domain

import blackjack.fixture.cardFixture
import blackjack.fixture.dealerFixture
import blackjack.fixture.handsFixture
import blackjack.fixture.playerFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 첫 2장의 카드가 16점 이하라면 한 장 더 히트한다." {
        val deck =
            Deck(
                listOf(
                    Card(Suit.SPADE, Rank.TEN),
                    Card(Suit.SPADE, Rank.SIX),
                    Card(Suit.SPADE, Rank.TWO),
                ),
            )
        val actual = Dealer("dealer")

        actual.initialDraw(deck)

        actual.score shouldBeGreaterThan 16
        actual.handSize shouldBe 3
    }

    "딜러는 첫 2장의 카드가 17점 이상이라면 히트하지 않는다." {
        val deck =
            Deck(
                listOf(
                    Card(Suit.SPADE, Rank.TEN),
                    Card(Suit.SPADE, Rank.SEVEN),
                    Card(Suit.SPADE, Rank.TWO),
                ),
            )
        val actual = Dealer("dealer")

        actual.initialDraw(deck)

        actual.score shouldBe 17
        actual.handSize shouldBe 2
        actual.status shouldBe ParticipantStatus.STAY
    }

    "딜러가 버스트한 경우 플레이어의 손패 상관없이 패배한다." {
        val dealer = dealerFixture(status = ParticipantStatus.BURST)
        val burstPlayer = playerFixture(status = ParticipantStatus.BURST)
        val stayPlayer = playerFixture(status = ParticipantStatus.STAY)

        val actual1 = dealer vs burstPlayer
        val actual2 = dealer vs stayPlayer

        actual1 shouldBe Result.LOSE
        actual2 shouldBe Result.LOSE
    }

    "딜러가 버스트하지 않은 경우 플레이어와 비교하여 결과를 반환한다." {
        val dealerHands =
            handsFixture(
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.TEN),
            )
        val dealer = dealerFixture(hands = dealerHands)

        forAll(
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.TEN),
                ),
                ParticipantStatus.STAY,
                Result.DRAW,
            ),
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.TEN),
                ),
                ParticipantStatus.BURST,
                Result.WIN,
            ),
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.ACE),
                ),
                ParticipantStatus.STAY,
                Result.LOSE,
            ),
        ) { playerHands, playerStatus, expected ->
            val player = playerFixture(hands = playerHands, status = playerStatus)

            val actual = dealer vs player

            actual shouldBe expected
        }
    }
})
