package blackjack.domain.card

import blackjack.SpadeAce
import blackjack.SpadeJack
import blackjack.SpadeKing
import blackjack.SpadeQueen
import blackjack.SpadeTen
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
        var playingCards = PlayingCards(listOf())

        // when
        playingCards = playingCards.add(PlayingCard(Suit.CLUBS, Denomination.ACE))
        val expected = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // then
        assertThat(playingCards).isEqualTo(expected)
    }

    @Test
    fun `카드 목록 - 추가 실패 예외처리 테스트, 카드를 중복 입력하는 경우`() {
        // given
        val playingCards = PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // when
        val exception = assertThrows<IllegalArgumentException> {
            playingCards.add(PlayingCard(Suit.CLUBS, Denomination.ACE))
        }

        // then
        assertThat(exception.message).isEqualTo("중복된 카드는 추가할 수 없습니다.")
    }

    @Test
    fun `카드 목록 - Bust 여부 확인 테스트`() {
        // given
        val playingCards = PlayingCards(
            PlayingCard(Suit.SPADES, Denomination.JACK),
            PlayingCard(Suit.SPADES, Denomination.QUEEN),
            PlayingCard(Suit.SPADES, Denomination.TWO)
        )

        // when, then
        assertThat(playingCards.isBust()).isTrue
    }

    @Test
    fun `카드 목록 - Stay 여부 확인 테스트`() {
        // given
        val playingCards = PlayingCards(
            PlayingCard(Suit.SPADES, Denomination.FIVE),
            PlayingCard(Suit.SPADES, Denomination.SIX),
            PlayingCard(Suit.SPADES, Denomination.JACK)
        )

        // when, then
        assertThat(playingCards.isStay()).isTrue
    }

    @ParameterizedTest
    @MethodSource("providePlayingCards")
    fun `카드 목록 - BlackJack 여부 확인 테스트`(given: PlayingCards, expected: Boolean) {
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
