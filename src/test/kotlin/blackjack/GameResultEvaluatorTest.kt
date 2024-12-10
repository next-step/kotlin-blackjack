package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Suit
import blackjack.domain.calculateStatistics
import blackjack.infra.AmountStatistics
import blackjack.infra.AmountStatisticsBuilder
import blackjack.ui.DisplayCard
import blackjack.ui.RoundResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultEvaluatorTest {
    @Test
    fun `라운드 카드와 점수를 알 수 있다`() {
        val players = Players.from(listOf("userA", "userB"))
        val dealer = Dealer()
        players.deal(
            players.find("userA"),
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE))),
        )
        players.deal(
            players.find("userB"),
            Deck(listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.ACE, Suit.SPADE))),
        )
        dealer.receive(
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.THREE, Suit.SPADE))),
        )
        val gameResultEvaluator = GameResultEvaluator(players, dealer)

        val actual = gameResultEvaluator.evaluateRounds()

        assertAll(
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "userA",
                        listOf(
                            DisplayCard.from(CardRank.TWO.name, Suit.HEART.name),
                            DisplayCard.from(CardRank.TWO.name, Suit.SPADE.name),
                        ),
                        4,
                    ),
                )
            },
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "userB",
                        listOf(
                            DisplayCard.from(CardRank.ACE.name, Suit.HEART.name),
                            DisplayCard.from(CardRank.ACE.name, Suit.SPADE.name),
                        ),
                        12,
                    ),
                )
            },
            {
                assertThat(
                    actual,
                ).contains(
                    RoundResult(
                        "딜러",
                        listOf(
                            DisplayCard.from(CardRank.TWO.name, Suit.HEART.name),
                            DisplayCard.from(CardRank.THREE.name, Suit.SPADE.name),
                        ),
                        5,
                    ),
                )
            },
        )
    }

    @ParameterizedTest
    @MethodSource("statisticsMoneyData")
    fun `딜러와 플레이어의 최종 결과를 알 수 있다`(
        playerCards: List<Card>,
        dealerCards: List<Card>,
        userExpected: Int,
        dealerExpected: Int,
    ) {
        val player = Player.from("철수")
        val players = Players(listOf(player))
        val dealer = Dealer()
        player.betting(Money(1_000))
        player.receive(Deck(playerCards))
        dealer.receive(Deck(dealerCards))

        val amountStatistics: AmountStatistics = dealer.calculateStatistics(players, AmountStatisticsBuilder())

        assertThat(amountStatistics.playerProfits).isEqualTo(mapOf("철수" to Money(userExpected)))
        assertThat(amountStatistics.dealerProfit).isEqualTo(Money(dealerExpected))
    }

    companion object {
        @JvmStatic
        fun statisticsMoneyData(): Stream<Arguments> {
            return Stream.of(
                // 플레이어가 이기는 경우
                Arguments.of(
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_000,
                    0,
                ),
                // 플레이어가 패배한 경우
                Arguments.of(
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_000,
                    0,
                ),
                // 플레이어가 블랙잭인 경우
                Arguments.of(
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    -1_000,
                    1_000,
                ),
                // 플레이어가 버스트인 경우
                Arguments.of(
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.KING, Suit.SPADE)),
                    listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    1_500,
                    -500,
                ),
                // 딜러가 버스트인 경우
                Arguments.of(
                    listOf(
                        Card(CardRank.ACE, Suit.HEART),
                        Card(CardRank.KING, Suit.SPADE),
                        Card(CardRank.KING, Suit.SPADE),
                    ),
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.KING, Suit.SPADE)),
                    -1_000,
                    1_000,
                ),
                Arguments.of(
                    // 플레이어가 비기는 경우
                    listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)),
                    listOf(
                        Card(CardRank.KING, Suit.HEART),
                        Card(CardRank.KING, Suit.SPADE),
                        Card(CardRank.QUEEN, Suit.SPADE),
                    ),
                    1_000,
                    0,
                ),
            )
        }
    }
}
