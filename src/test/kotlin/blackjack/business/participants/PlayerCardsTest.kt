package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FOUR
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
        val actual = playerCards.add(SPACE_ACE)

        // then
        actual.size shouldBe 1
        actual.cards[0] shouldBe SPACE_ACE
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `카드의 합을 구한다`(cards: List<Card>, expected: Int) {
        // given
        val playerCards = PlayerCards()
        val target = playerCards.addAll(cards)

        // when
        val actual = target.sum()

        // then
        actual shouldBe expected
    }

    @Test
    fun `카드의 합이 21을 초과하는지 확인`() {
        // given
        val playerCards = PlayerCards()
        val target = playerCards.addAll(listOf(SPACE_TEN, SPACE_EIGHT, SPACE_FOUR))

        // when
        val actual = target.isBust()

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
        val actual = playerCards.addAll(listOf(SPACE_ACE, SPACE_EIGHT))

        // then
        actual.size shouldBe 2
        actual.cards[0] shouldBe SPACE_ACE
        actual.cards[1] shouldBe SPACE_EIGHT
    }

    @Test
    fun `카드가 2장이고 합이 21이면 NaturalBlackJack가 맞다`() {
        // given
        val playerCards = PlayerCards(SPACE_ACE, SPACE_TEN)

        // when
        val actual = playerCards.isNaturalBlackJack()

        // then
        actual shouldBe true
    }

    @Test
    fun `카드가 2장이고 합이 21이 아니면 NaturalBlackJack가 아니다`() {
        // given
        val playerCards = PlayerCards(SPACE_ACE, SPACE_EIGHT)

        // when
        val actual = playerCards.isNaturalBlackJack()

        // then
        actual shouldBe false
    }

    @Test
    fun `카드가 3장이고 합이 21이여도 NaturalBlackJack가 아니다`() {
        // given
        val playerCards = PlayerCards(SPACE_ACE, SPACE_EIGHT, SPACE_TWO)

        // when
        val actual = playerCards.isNaturalBlackJack()

        // then
        actual shouldBe false
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
