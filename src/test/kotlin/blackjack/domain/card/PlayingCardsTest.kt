package blackjack.domain.card

import blackjack.SpadeAce
import blackjack.SpadeFive
import blackjack.SpadeJack
import blackjack.SpadeKing
import blackjack.SpadeQueen
import blackjack.SpadeSix
import blackjack.SpadeTen
import blackjack.SpadeTwo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PlayingCardsTest {
    @Test
    fun `카드 목록 - 추가 테스트`() {
        // given
        var cards = PlayingCards(listOf())

        // when
        cards = cards.plus(SpadeAce)
        val expected = PlayingCards(SpadeAce)

        // then
        assertThat(cards).isEqualTo(expected)
    }

    @Test
    fun `카드 목록 - 추가 실패 예외처리 테스트, 카드를 중복 입력하는 경우`() {
        // given
        val cards = PlayingCards(SpadeAce)

        // when
        val exception = assertThrows<IllegalArgumentException> {
            cards.plus(SpadeAce)
        }

        // then
        assertThat(exception.message).isEqualTo("중복된 카드는 추가할 수 없습니다.")
    }

    @Test
    fun `카드 목록 - Bust(Jack + Queen + Two = 22)`() {
        // given
        val cards = PlayingCards(SpadeJack, SpadeQueen, SpadeTwo)

        // when, then
        assertThat(cards.isBust()).isTrue
    }

    @Test
    fun `카드 목록 - Stay(Five + Six + Jack = 21)`() {
        // given
        val cards = PlayingCards(SpadeFive, SpadeSix, SpadeJack)

        // when, then
        assertThat(cards.isStay()).isTrue
    }

    @ParameterizedTest
    @MethodSource("providePlayingCards")
    fun `카드 목록 - BlackJack`(given: PlayingCards, expected: Boolean) {
        // when
        val actual = given.isBlackjack()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun providePlayingCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(PlayingCards(SpadeAce, SpadeJack, SpadeQueen), false),
                Arguments.of(PlayingCards(SpadeAce), false),
                Arguments.of(PlayingCards(SpadeAce, SpadeAce), false),
                Arguments.of(PlayingCards(SpadeAce, SpadeTen), true),
                Arguments.of(PlayingCards(SpadeAce, SpadeJack), true),
                Arguments.of(PlayingCards(SpadeAce, SpadeQueen), true),
                Arguments.of(PlayingCards(SpadeAce, SpadeKing), true)
            )
        }
    }
}
