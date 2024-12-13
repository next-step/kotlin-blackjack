package blackjack.domain

import blackjack.fixture.cardFixture
import blackjack.fixture.dealerFixture
import blackjack.fixture.handsFixture
import blackjack.fixture.playerFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 첫 2장의 카드가 16점 이하라면 한 장 더 히트할 수 있다." {
        val deck =
            Deck(
                listOf(
                    Card(Suit.SPADE, Rank.TEN),
                    Card(Suit.SPADE, Rank.TWO),
                    Card(Suit.SPADE, Rank.THREE),
                ),
            )
        val actual = Dealer("dealer")

        actual.initialDraw(deck)

        actual.shouldDraw() shouldBe true
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
    }

    "딜러가 버스트한 경우 플레이어들은 손패 상관없이 승리한다." {
        val burstHands =
            handsFixture(
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.TEN),
            )
        val dealer = dealerFixture(hands = burstHands)
        val normalPlayer = playerFixture()
        val burstPlayer = playerFixture(hands = burstHands)

        val actual1 = dealer vs normalPlayer
        val actual2 = dealer vs burstPlayer

        actual1 shouldBe Result.WIN
        actual2 shouldBe Result.WIN
    }

    "딜러가 버스트하지 않은 경우 플레이어와 비교하여 결과를 반환하고 베팅금을 조정한다." {
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
                Result.DRAW,
                10_000,
            ),
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.TEN),
                ),
                Result.LOSE,
                0,
            ),
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.ACE),
                ),
                Result.WIN,
                15_000,
            ),
            row(
                handsFixture(
                    cardFixture(rank = Rank.TEN),
                    cardFixture(rank = Rank.FIVE),
                    cardFixture(rank = Rank.SIX),
                ),
                Result.WIN,
                10_000,
            ),
        ) { playerHands, result, betIncome ->
            val player =
                playerFixture(
                    hands = playerHands,
                    bet = Bet(10_000),
                )

            val actual = dealer vs player

            actual shouldBe result
            player.profit shouldBe betIncome
        }
    }
})
