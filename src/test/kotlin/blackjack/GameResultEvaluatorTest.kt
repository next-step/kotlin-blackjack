package blackjack

import blackjack.domain.CardRank
import blackjack.domain.Card
import blackjack.domain.Suit
import blackjack.domain.Deck
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.ui.DealerResult
import blackjack.ui.FinalWinnerResults
import blackjack.ui.RoundResult
import blackjack.ui.UIMatchType
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
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.TWO, Suit.SPADE)))
        )
        players.deal(
            players.find("userB"),
            Deck(listOf(Card(CardRank.ACE, Suit.HEART), Card(CardRank.ACE, Suit.SPADE)))
        )
        dealer.receive(
            Deck(listOf(Card(CardRank.TWO, Suit.HEART), Card(CardRank.THREE, Suit.SPADE)))
        )

        val gameResultEvaluator = GameResultEvaluator(players, dealer)
        val actual = gameResultEvaluator.evaluateRounds()

        assertAll(
            { assertThat(actual).contains(RoundResult("userA", mapOf("TWO" to listOf("HEART", "SPADE")), 4)) },
            { assertThat(actual).contains(RoundResult("userB", mapOf("ACE" to listOf("HEART", "SPADE")), 12)) },
            {
                assertThat(actual).contains(
                    RoundResult(
                        DEALER_NAME,
                        mapOf("TWO" to listOf("HEART"), "THREE" to listOf("SPADE")),
                        5
                    )
                )
            },
        )
    }

    @ParameterizedTest
    @MethodSource("winMatchEvaluateData")
    fun `딜러와 플레이어의 최종 결과를 알 수 있다`(
        userACards: List<Card>,
        userBCards: List<Card>,
        dealerCards: List<Card>,
        userAResult: UIMatchType,
        userBResult: UIMatchType,
        dealerResult: DealerResult
    ) {
        val players = Players.from(listOf("userA", "userB"))
        val dealer = Dealer()
        players.deal(
            players.find("userA"),
            Deck(userACards)
        )
        players.deal(
            players.find("userB"),
            Deck(userBCards)
        )
        dealer.receive(
            Deck(dealerCards)
        )

        val gameResultEvaluator = GameResultEvaluator(players, dealer)
        val actual: FinalWinnerResults = gameResultEvaluator.winMatchEvaluate()

        assertAll(
            { assertThat(actual.playerResults["userA"]).isEqualTo(userAResult) },
            { assertThat(actual.playerResults["userB"]).isEqualTo(userBResult) },
            { assertThat(actual.dealerResult).isEqualTo(dealerResult) },
        )
    }

    companion object {
        @JvmStatic
        fun winMatchEvaluateData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        Card(CardRank.TWO, Suit.HEART),
                        Card(CardRank.TWO, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.TWO, Suit.HEART),
                        Card(CardRank.THREE, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.ACE, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    UIMatchType.LOSS,
                    UIMatchType.LOSS,
                    DealerResult(2, 0, 0),
                ),
                Arguments.of(
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.TWO, Suit.DIAMOND),
                    ),
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    UIMatchType.LOSS,
                    UIMatchType.DRAW,
                    DealerResult(1, 0, 1),
                ),
                Arguments.of(
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.ACE, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.ACE, Suit.SPADE),
                    ),
                    listOf(
                        Card(CardRank.JACK, Suit.DIAMOND),
                        Card(CardRank.JACK, Suit.HEART),
                        Card(CardRank.JACK, Suit.SPADE),
                    ),
                    UIMatchType.WIN,
                    UIMatchType.WIN,
                    DealerResult(0, 2, 0),
                ),
            )
        }
    }
}