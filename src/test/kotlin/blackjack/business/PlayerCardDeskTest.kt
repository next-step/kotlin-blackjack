package blackjack.business

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_THEN
import blackjack.business.CardFixture.SPACE_THREE
import blackjack.business.CardFixture.SPACE_TWO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("플레이어 카드")
class PlayerCardDeskTest {

    @Test
    fun `카드를 추가한다`() {
        // given
        val playerCards = PlayerCards()

        // when
        playerCards.add(SPACE_ACE)

        // then
        playerCards.size shouldBe 1
        playerCards[0] shouldBe SPACE_ACE
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `카드의 합을 구한다`(cards: List<Card>, expected: Int) {
        // given
        val playerCards = PlayerCards()
        cards.forEach { playerCards.add(it) }

        // when
        val actual = playerCards.sum()

        // then
        actual shouldBe expected
    }

    companion object {
        @JvmStatic
        fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        SPACE_ACE, SPACE_EIGHT
                    ),
                    19
                ),
                Arguments.of(
                    listOf(
                        SPACE_ACE,
                        SPACE_ACE,
                        SPACE_ACE,
                        SPACE_EIGHT,
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        SPACE_ACE,
                        SPACE_EIGHT,
                        SPACE_TWO
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        SPACE_ACE,
                        SPACE_EIGHT,
                        SPACE_TWO,
                        SPACE_THEN
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        SPACE_ACE,
                        SPACE_EIGHT,
                        SPACE_THREE
                    ),
                    12
                ),
                Arguments.of(
                    listOf(
                        SPACE_ACE,
                        SPACE_NINE,
                        SPACE_THEN
                    ),
                    20
                ),
            )
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["ACE,EIGHT,true", "ACE,ACE,true", "ACE,TEN,false", "ACE,NINE,true"])
    fun `카드를 추가할 수 있는지 확인`(firstRank: Rank, secondRank: Rank, expected: Boolean) {
        // given
        val playerCards = PlayerCards()
        playerCards.add(Card(Suit.SPADE, firstRank))
        playerCards.add(Card(Suit.SPADE, secondRank))

        // when
        val actual = playerCards.canDrawCard()

        // then
        actual shouldBe expected
    }
}
