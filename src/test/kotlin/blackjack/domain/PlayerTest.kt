package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerTest {
    @DisplayName("Player 이름은 공백이 될 수 없다")
    @ParameterizedTest
    @EmptySource
    fun emptyName(name: String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Player(name) }
    }

    @DisplayName("Player 게임 시작 전 2개의 카드를 받는다.")
    @ParameterizedTest
    @MethodSource("provideInitialCardS")
    fun readyToPlayGameWithInitialCards(cards: List<Card>) {
        val player = Player("고니").apply { readyToPlay(cards) }
        assertThat(player.cards.size).isEqualTo(cards.size)
    }

    companion object {
        @JvmStatic
        fun provideInitialCardS(): Stream<List<Card>> =
            Stream.of(listOf(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.ACE, CardShape.CLOVER)))
    }
}
