package blackjack.domain.participant

import blackjack.domain.GameProfit.GameProfit
import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class ParticipantTest : StringSpec({
    "플레이어의 이름이 빈칸 혹은 공백이면 예외를 발생한다." {
        listOf(
            " ",
            ""
        ).forEach {
            // when //then
            shouldThrowExactly<IllegalArgumentException> { Player(it) }
        }
    }

    "플레이어가 한장의 카드를 뽑는다." {
        // given
        val player = Player("김경록")

        // when
        player.drawCard(Card(Suit.CLOVER, Face.ACE))

        // then
        player.cards.size shouldBe 1
    }

    "플레이어가 여러장의 카드를 뽑는다." {
        // given
        val player = Player("김경록")

        // when
        player.drawCards(
            listOf(
                Card(Suit.CLOVER, Face.ACE),
                Card(Suit.SPADE, Face.TWO),
            )
        )

        // then
        player.cards.size shouldBe 2
    }

    "플레이어가 카드를 더 받을 수 있는 상태인지 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.THREE),
                    Card(Suit.DIAMOND, Face.TEN),
                ),
                true
            ),
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.TWO),
                    Card(Suit.DIAMOND, Face.TEN),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                false
            ),
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.ACE),
                    Card(Suit.DIAMOND, Face.TEN),
                ),
                false
            ),
        ).forEach { (cardGroup, expected) ->
            // given
            val player = Player(
                "김경록",
                Cards(cardGroup),
            )

            // when
            val actual = player.isAbleToDraw()

            // then
            actual shouldBe expected
        }
    }

    "플레이어의 턴이 종료되면 플레이어가 카드를 더 받을 수 없는 상태가 된다." {
        // given
        val player = Player("김경록", Cards())

        // when
        player.endOwnTurn()

        // then
        player.isAbleToDraw() shouldBe false
    }

    "플레이어의 초기 카드를 오픈한다." {
        // given
        val player = Player(
            "김경록",
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.ACE),
                    Card(Suit.CLOVER, Face.TWO),
                )
            )
        )

        // when
        val actual = player.openInitCards()

        // then
        actual.size shouldBe 2
        actual shouldContainExactlyInAnyOrder listOf(Card(Suit.DIAMOND, Face.ACE), Card(Suit.CLOVER, Face.TWO))
    }

    "딜러의 초기 카드를 오픈한다." {
        // given
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.ACE),
                    Card(Suit.CLOVER, Face.TWO),
                )
            )
        )

        // when
        val actual = dealer.openInitCards()

        // then
        actual.size shouldBe 1
        actual shouldContainExactlyInAnyOrder listOf(Card(Suit.DIAMOND, Face.ACE))
    }

    "딜러가 카드를 더 받을 수 있는 상태인지 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.TEN),
                    Card(Suit.DIAMOND, Face.SIX),
                ),
                true
            ),
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.TEN),
                    Card(Suit.DIAMOND, Face.SEVEN),
                ),
                false
            ),
        ).forEach { (cardGroup, expected) ->
            // given
            val dealer = Dealer(
                Cards(cardGroup),
            )

            // when
            val actual = dealer.isAbleToDraw()

            // then
            actual shouldBe expected
        }
    }

    "플레이어와 딜러가 비기면 이익이 없다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN),
                ),
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.TEN),
                    Card(Suit.CLOVER, Face.ACE),
                ),
                mutableListOf(
                    Card(Suit.CLOVER, Face.ACE),
                    Card(Suit.CLOVER, Face.TEN),
                ),
            ),
        ).forEach { (playerCards, dealerCards) ->
            val playerName = "경록"
            val player = Player(
                playerName,
                Cards(playerCards),
                BettingMoney(1_000L)
            )

            val dealer = Dealer(
                Cards(
                    dealerCards
                )
            )

            // when
            val actual = dealer.decideWinOrLoseResults(listOf(player))

            // then
            actual shouldHaveSize 1
            actual[0].name shouldBe playerName
            actual[0].gameProfits shouldHaveSize 1
            actual[0].gameProfits[0] shouldBe GameProfit.NONE
        }
    }

    "플레이어가 블랙잭으로 이긴경우, 플레이어는 1.5배의 이익을 얻는다." {
        val playerName = "경록"
        val player = Player(
            playerName,
            Cards(
                mutableListOf(
                    Card(Suit.CLOVER, Face.ACE),
                    Card(Suit.CLOVER, Face.TEN)
                )
            ),
            BettingMoney(1_000L)
        )

        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN)
                )
            )
        )

        // when
        val actual = dealer.decideWinOrLoseResults(listOf(player))

        // then
        actual shouldHaveSize 1
        actual[0].name shouldBe playerName
        actual[0].gameProfits shouldHaveSize 1
        actual[0].gameProfits[0] shouldBe GameProfit(BigDecimal.valueOf(1_500.0))
    }

    "플레이어가 블랙잭이 아닌 경우, 플레이어들의 게임 결과를 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN),
                    Card(Suit.CLOVER, Face.ACE)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_000L))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_000L))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.ZERO)
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SIX),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(-1_000L))
            )
        ).forEach { (playerCards, bettingMoney, expectedResult) ->
            val playerName = "경록"
            val player = Player(
                playerName,
                Cards(playerCards),
                bettingMoney
            )

            val dealer = Dealer(
                Cards(
                    mutableListOf(
                        Card(Suit.CLOVER, Face.SEVEN),
                        Card(Suit.CLOVER, Face.TEN)
                    )
                )
            )

            // when
            val actual = dealer.decideWinOrLoseResults(listOf(player))

            // then
            actual shouldHaveSize 1
            actual[0].name shouldBe playerName
            actual[0].gameProfits shouldHaveSize 1
            actual[0].gameProfits[0] shouldBe expectedResult
        }
    }

    "플레이어가 블랙잭이 아닌 경우, 딜러의 게임 결과를 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.ZERO),
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_000L))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SIX),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(-1_000L))
            )
        ).forEach { (dealerCards, playerBettingMoney, expectedResult) ->
            val playerName = "경록"
            val player = Player(
                playerName,
                Cards(
                    mutableListOf(
                        Card(Suit.CLOVER, Face.SEVEN),
                        Card(Suit.CLOVER, Face.TEN)
                    )
                ),
                playerBettingMoney
            )

            val dealer = Dealer(
                Cards(
                    dealerCards
                )
            )

            // when
            val actual = dealer.getDealerResult(listOf(player))

            // then
            actual.name shouldBe "딜러"
            actual.gameProfits shouldHaveSize 1
            actual.gameProfits[0] shouldBe expectedResult
        }
    }
})
