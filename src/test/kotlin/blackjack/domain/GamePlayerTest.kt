package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.domain.Finished.Bust
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

internal class GamePlayerTest {
    @ParameterizedTest
    @EmptySource
    fun `Player 이름은 공백이 될 수 없다`(name: String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { GamePlayer(name) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["고니", "아귀", "팽 경장", "너구리", "정 마담", "고광렬"])
    fun `Player 이름을 가질 수 있다`(name: String) {
        assertThat(GamePlayer(name).name).isEqualTo(name)
    }

    @ParameterizedTest
    @MethodSource("provideInitialCardS")
    fun `Player 게임 시작 전 2개의 카드를 받으면 게임 참가할 수 있다`(cards: List<Card>) {
        val gamePlayer = GamePlayer("고니").apply { cards.forEach(this::draw) }
        assertThat(gamePlayer.cards.size).isEqualTo(cards.size)
        assertThat(gamePlayer.shouldBeReadyToPlay()).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideInitialInvalidCardS")
    fun `Player 게임 시작 전 2개의 카드를 받지 않으면 게임 참가할 상태가 되지 않는다`(cards: List<Card>) {
        val gamePlayer = GamePlayer("고니").apply { cards.forEach(this::draw) }
        assertThat(gamePlayer.shouldBeReadyToPlay()).isFalse
    }

    @ParameterizedTest
    @MethodSource("provideHitCard")
    fun `Player 히트를 외치면 카드 한장을 더 받는다`(initialCards: List<Card>, hitCard: Card) {
        val gamePlayer = GamePlayer("고니").apply {
            initialCards.forEach(this::draw)
            draw(hitCard)
        }
        assertThat(gamePlayer.cards.value.last()).isEqualTo(hitCard)
    }

    @ParameterizedTest
    @MethodSource("provideBustCards")
    fun `Player 카드 합산이 21 초과 bust 상태가 되어 게임을 더 이상 참가할 수 없다`(initialCards: List<Card>, hitCard: Card) {
        val gamePlayer = GamePlayer("고니").apply {
            initialCards.forEach(this::draw)
            draw(hitCard)
        }
        assertThat(gamePlayer.state is Bust).isTrue
        assertThat(gamePlayer.finished).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideNotBustCards")
    fun `Player 카드 합산이 21 미만 bust 상태가 되어 게임을 계속 할 수있다`(initialCards: List<Card>, hitCard: Card) {
        val gamePlayer = GamePlayer("고니").apply {
            initialCards.forEach(this::draw)
            draw(hitCard)
        }
        assertThat(gamePlayer.state is Bust).isFalse
        assertThat(gamePlayer.finished).isFalse
    }

    @Test
    fun `Player 카드가 2장이고 합산이 21이면 블랙잭 완성`() {
        val cards = listOf(Card(CardType.KING, CardShape.HEART), Card(CardType.ACE, CardShape.DIAMOND))
        val gamePlayer = GamePlayer("고니").apply { cards.forEach(this::draw) }
        assertThat(gamePlayer.state is Blackjack).isTrue
        assertThat(gamePlayer.finished).isTrue
    }

    @Test
    fun `Player 카드가 2장 쵸과 합산이 21이면 블랙잭이 아니다`() {
        val cards = listOf(
            Card(CardType.THREE, CardShape.HEART),
            Card(CardType.EIGHT, CardShape.DIAMOND),
            Card(CardType.TEN, CardShape.SPADE)
        )
        val gamePlayer = GamePlayer("고니").apply { cards.forEach(this::draw) }
        assertThat(gamePlayer.state is Blackjack).isFalse
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
        fun provideBustCards(): Stream<Arguments> =
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
        fun provideNotBustCards(): Stream<Arguments> =
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
