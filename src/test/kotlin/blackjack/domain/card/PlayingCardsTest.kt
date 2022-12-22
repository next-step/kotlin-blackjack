package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
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

    @ParameterizedTest
    @CsvSource(value = ["ACE,JACK,QUEEN:false", "TWO,JACK,QUEEN:true"], delimiter = ':')
    fun `카드 목록 - Bust 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isBust()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("providePlayingCards")
    fun `카드 목록 - BlackJack 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isBlackjack()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun providePlayingCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("ACE,JACK,QUEEN", false),
                Arguments.of("ACE", false),
                Arguments.of("ACE,ACE", false),
                Arguments.of("ACE,TEN", true),
                Arguments.of("ACE,JACK", true),
                Arguments.of("ACE,QUEEN", true),
                Arguments.of("ACE,KING", true)
            )
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["ACE,JACK,QUEEN:true", "TWO,JACK,QUEEN:false"], delimiter = ':')
    fun `카드 목록 - Stay 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isStay()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
