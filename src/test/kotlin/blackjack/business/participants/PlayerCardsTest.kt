package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_THREE
import blackjack.business.CardFixture.SPACE_TWO
import blackjack.business.card.Card
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("플레이어 카드")
class PlayerCardsTest {

    @Test
    fun `카드를 추가한다`() {
        // given
        val playerCards = PlayerCards()

        // when
        playerCards.add(SPACE_ACE)

        // then
        playerCards.size shouldBe 1
        playerCards.cards[0] shouldBe SPACE_ACE
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

    @Test
    fun `카드의 합이 21을 초과하는지 확인`() {
        // given
        val playerCards = PlayerCards()
        playerCards.add(SPACE_TEN)
        playerCards.add(SPACE_NINE)
        playerCards.add(SPACE_THREE)

        // when
        val actual = playerCards.isBust()

        // then
        actual shouldBe true
    }

    @Test
    fun `카드의 합이 21을 초과하지 않는지 확인`() {
        // given
        val playerCards = PlayerCards()
        playerCards.add(SPACE_TEN)
        playerCards.add(SPACE_EIGHT)
        playerCards.add(SPACE_THREE)

        // when
        val actual = playerCards.isBust()

        // then
        actual shouldBe false
    }

    @Test
    fun `여러 카드를 추가할 수 있다`() {
        // given
        val playerCards = PlayerCards()

        // when
        playerCards.addAll(listOf(SPACE_ACE, SPACE_EIGHT))

        // then
        playerCards.size shouldBe 2
        playerCards.cards[0] shouldBe SPACE_ACE
        playerCards.cards[1] shouldBe SPACE_EIGHT
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
                        SPACE_TEN
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
                        SPACE_TEN
                    ),
                    20
                ),
            )
        }
    }
}
