package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DealerTest {

    @Test
    fun `카드 딜링 테스트`() {
        val dealer = Dealer()
        val cards = dealer.dealingTwoCards()
        assertAll(
            { assertThat(cards).hasSize(2) },
            { assertThat(cards[0]).isNotEqualTo(cards[1]) }
        )
    }

    @Test
    fun `카드가 다 소진되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val dealer = Dealer()
            repeat(26) { dealer.dealingTwoCards() }
        }
    }

    @Test
    fun `딜러의 이름은 항상 딜러이다`() {
        val dealer = Dealer()
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `초기 dealing 카드 합이 17 이상이면 더이상 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.SPADE, Number.SEVEN)))
        val hit = dealer.moreCard()
        assertAll(
            { assertThat(hit).isFalse() },
            { assertThat(dealer.cards).hasSize(2) },
            { assertThat(dealer.hit).isFalse() }
        )
    }

    @Test
    fun `초기 dealing 카드 합이 16 이하이면 카드를 한 장 더 받는다`() {
        val dealer = Dealer()
        dealer.addCards(listOf(Card(Suit.SPADE, Number.QUEEN), Card(Suit.SPADE, Number.SIX)))
        val hit = dealer.moreCard()
        assertAll(
            { assertThat(hit).isTrue() },
            { assertThat(dealer.cards).hasSize(3) },
            { assertThat(dealer.hit).isFalse() }
        )
    }

    @Test
    fun `초기 카드 dealing을 받으면 무조건 두 장의 카드를 소지하고 있다`() {
        val dealer = Dealer()
        dealer.initialCardDealing()
        assertThat(dealer.cards).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("compareTestInput")
    fun `승패를 결정한다`(
        dealerCards: List<Card>,
        player1Cards: List<Card>,
        player2Cards: List<Card>,
        expectedWinning: Int,
        expectedLosing: Int
    ) {
        // given
        val dealer = Dealer()
        val players = Players(listOf(Player("a"), Player("b")))
        dealer.addCards(dealerCards)
        players.values[0].addCards(player1Cards)
        players.values[1].addCards(player2Cards)

        // when
        dealer.compareWithPlayers(players)

        // then
        assertAll(
            { assertThat(dealer.blackJackResult?.winning).isEqualTo(expectedWinning) },
            { assertThat(dealer.blackJackResult?.losing).isEqualTo(expectedLosing) }
        )
    }

    companion object {
        @JvmStatic
        fun compareTestInput(): Stream<Arguments> {
            return Stream.of(
                // player1 < dealer < player2 < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.SPADE, Number.KING)),
                    listOf(Card(Suit.HEART, Number.FOUR), Card(Suit.CLUB, Number.NINE)),
                    1,
                    1
                ),
                // player1 < 21 < dealer, player2
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.KING), Card(Suit.DIAMOND, Number.JACK)),
                    listOf(Card(Suit.SPADE, Number.KING), Card(Suit.SPADE, Number.TEN), Card(Suit.SPADE, Number.THREE)),
                    listOf(Card(Suit.HEART, Number.FOUR), Card(Suit.CLUB, Number.NINE)),
                    0,
                    1
                ),
                // player1 = dealer < player2 < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.EIGHT)),
                    listOf(Card(Suit.HEART, Number.JACK), Card(Suit.CLUB, Number.NINE)),
                    0,
                    1
                ),
                // player1 < dealer = player2 < 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.THREE)),
                    listOf(Card(Suit.HEART, Number.ACE), Card(Suit.CLUB, Number.FOUR)),
                    1,
                    0
                ),
                // player1 < dealer < 21 < player2
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.THREE)),
                    listOf(Card(Suit.HEART, Number.JACK), Card(Suit.CLUB, Number.KING), Card(Suit.CLUB, Number.FOUR)),
                    2,
                    0
                ),
                // dealer < player1 < 21 < player2
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.FIVE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.QUEEN)),
                    listOf(Card(Suit.HEART, Number.JACK), Card(Suit.CLUB, Number.KING), Card(Suit.CLUB, Number.TWO)),
                    1,
                    1
                ),
                // player1 < dealer = player2 = 21
                Arguments.of(
                    listOf(Card(Suit.DIAMOND, Number.QUEEN), Card(Suit.DIAMOND, Number.ACE)),
                    listOf(Card(Suit.SPADE, Number.SEVEN), Card(Suit.SPADE, Number.FOUR)),
                    listOf(Card(Suit.HEART, Number.JACK), Card(Suit.CLUB, Number.ACE)),
                    1,
                    0
                ),
            )
        }
    }
}
