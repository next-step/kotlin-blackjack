package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class PlayerTest {
    @ParameterizedTest
    @EmptySource
    fun `Player 이름은 공백이 될 수 없다`(name: String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Player(name) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["고니", "아귀", "팽 경장", "너구리", "정 마담", "고광렬"])
    fun `Player 이름을 가질 수 있다`(name: String) {
        assertThat(Player(name).name).isEqualTo(name)
    }

    @ParameterizedTest
    @MethodSource("provideInitialCardS")
    fun `Player 게임 시작 전 2개의 카드를 받는다`(cards: List<Card>) {
        val player = Player("고니").apply { readyToPlay(cards) }
        assertThat(player.cards.size).isEqualTo(cards.size)
    }

    @ParameterizedTest
    @MethodSource("provideInitialInvalidCardS")
    fun `Player 게임 시작 전 2개의 카드를 받지 않으면 에러가 발생한다`(cards: List<Card>) {
        val player = Player("고니")
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { player.readyToPlay(cards) }
    }

    @ParameterizedTest
    @MethodSource("provideHitCard")
    fun `Player 히트를 외치면 카드 한장을 더 받는다`(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.cards.value.last()).isEqualTo(hitCard)
    }

    @ParameterizedTest
    @MethodSource("provideBurstCards")
    fun `Player 카드 합산이 21 초과 burst 상태가 되어 게임을 더이상 참가할 수 없다`(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.burst()).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideNotBurstCards")
    fun `Player 카드 합산이 21 이상일 떄 burst 상태가 되어 게임을 계속 할 수있다`(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.burst()).isFalse
    }

    @Test
    fun `Player 카드 합산이 21이면 블랙잭 완성`() {
        val cards = Cards(mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.ACE, CardShape.DIAMOND)))
        val player = Player("고니", cards)
        assertThat(player.blackjack()).isTrue
    }

    companion object {
        @JvmStatic
        fun provideInitialCardS(): Stream<List<Card>> =
            Stream.of(listOf(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.ACE, CardShape.CLOVER)))

        @JvmStatic
        fun provideInitialInvalidCardS(): Stream<List<Card>> =
            Stream.of(listOf(Card(CardType.JACK, CardShape.CLOVER)), listOf())

        @JvmStatic
        fun provideHitCard(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.THREE, CardShape.CLOVER), Card(CardType.EIGHT, CardShape.HEART)),
                    Card(CardType.JACK, CardShape.DIAMOND)
                )
            )

        @JvmStatic
        fun provideBurstCards(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER)),
                    Card(CardType.TWO, CardShape.SPADE)
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER)),
                    Card(CardType.NINE, CardShape.SPADE)
                )
            )

        @JvmStatic
        fun provideNotBurstCards(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.FIVE, CardShape.CLOVER)),
                    Card(CardType.ACE, CardShape.SPADE)
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.TWO, CardShape.CLOVER)),
                    Card(CardType.FOUR, CardShape.SPADE)
                )
            )
    }
}
