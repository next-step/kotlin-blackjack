package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class CardsTest {

    @DisplayName("카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun cardDeck(cardDeckCount: Int) {
        assertThat(Cards(DEFAULT_CARD_DECK).value.size).isEqualTo(cardDeckCount)
    }

    @DisplayName("카드 목록에 카드를 추가할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCard")
    fun addCard(card: Card) {
        val cards = Cards().apply { add(card) }
        assertThat(cards.value.first()).isEqualTo(card)
    }

    @DisplayName("카드 목록 총 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCardsToSum")
    fun sum(initialCards: List<Card>, totalScore: Int) {
        val cards = Cards(initialCards.toMutableList())
        assertThat(cards.sum()).isEqualTo(totalScore)
    }

    @DisplayName("카드 목록 중에 ACE 포함되어 있고 총합이 21 이상인 경우 ACE default Score 1을 합산에 포함시킨다.")
    @ParameterizedTest
    @MethodSource("provideSumWithAce")
    fun sumWithAceDefaultScore(initialCards: List<Card>, totalScore: Int) {
        val cards = Cards(initialCards.toMutableList())
        assertThat(cards.sum()).isEqualTo(totalScore)
    }

    @DisplayName("카드 목록 중에 ACE 포함되어 있고 총합이 21 이하인 경우 ACE Special Score 11을 합산에 포함시킨다.")
    @ParameterizedTest
    @MethodSource("provideSumWithAceSpecialScore")
    fun sumWithAceSpecialScore(initialCards: List<Card>, totalScore: Int) {
        val cards = Cards(initialCards.toMutableList())
        assertThat(cards.sum()).isEqualTo(totalScore)
    }

    companion object {
        @JvmStatic
        fun provideCard(): Stream<Card> =
            Stream.of(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.ACE, CardShape.CLOVER))

        @JvmStatic
        fun provideCardsToSum(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER)), 20
                ),
                Arguments.of(
                    listOf(Card(CardType.TWO, CardShape.CLOVER), Card(CardType.EIGHT, CardShape.CLOVER)), 10
                )
            )

        @JvmStatic
        fun provideSumWithAce(): Stream<Arguments> {
            val ace = Card(CardType.ACE, CardShape.SPADE)
            return Stream.of(
                Arguments.of(
                    listOf(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.EIGHT, CardShape.CLOVER), ace),
                    19
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER), ace),
                    21
                ),
                Arguments.of(
                    listOf(Card(CardType.FOUR, CardShape.CLOVER), Card(CardType.NINE, CardShape.CLOVER), ace),
                    14
                )
            )
        }

        @JvmStatic
        fun provideSumWithAceSpecialScore(): Stream<Arguments> {
            val ace = Card(CardType.ACE, CardShape.SPADE)
            return Stream.of(
                Arguments.of(
                    listOf(Card(CardType.TWO, CardShape.CLOVER), Card(CardType.SIX, CardShape.CLOVER), ace),
                    19
                ),
                Arguments.of(
                    listOf(Card(CardType.FOUR, CardShape.CLOVER), Card(CardType.FIVE, CardShape.CLOVER), ace),
                    20
                ),
                Arguments.of(listOf(Card(CardType.KING, CardShape.CLOVER), ace), 21)
            )
        }
    }
}
