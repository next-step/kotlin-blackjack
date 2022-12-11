package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class PlayerTest {
    @DisplayName("Player 이름은 공백이 될 수 없다")
    @ParameterizedTest
    @EmptySource
    fun emptyName(name: String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Player(name) }
    }

    @DisplayName("Player 이름을 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = ["고니", "아귀", "팽 경장", "너구리", "정 마담", "고광렬"])
    fun name(name: String) {
        assertThat(Player(name).name).isEqualTo(name)
    }

    @DisplayName("Player 게임 시작 전 2개의 카드를 받는다.")
    @ParameterizedTest
    @MethodSource("provideInitialCardS")
    fun readyToPlayGameWithInitialCards(cards: List<Card>) {
        val player = Player("고니").apply { readyToPlay(cards) }
        assertThat(player.cards.size).isEqualTo(cards.size)
    }

    @DisplayName("Player 게임 시작 전 2개의 카드를 받지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInitialInvalidCardS")
    fun failToReadyToPlayGameWithInitialCards(cards: List<Card>) {
        val player = Player("고니")
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { player.readyToPlay(cards) }
    }

    @DisplayName("Player 히트를 외치면 카드 한장을 더 받는다.")
    @ParameterizedTest
    @MethodSource("provideHitCard")
    fun hit(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.cards.value.last()).isEqualTo(hitCard)
    }

    @DisplayName("Player 카드 합산이 21 초과 burst 상태가 되어 게임을 더이상 참가할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideBurstCards")
    fun burst(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.burst()).isTrue
    }

    @DisplayName("Player 카드 합산이 21 이상일 떄 burst 상태가 되어 게임을 계속 할 수있다.")
    @ParameterizedTest
    @MethodSource("provideNotBurstCards")
    fun notBurst(initialCards: List<Card>, hitCard: Card) {
        val player = Player("고니").apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(player.burst()).isFalse
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
