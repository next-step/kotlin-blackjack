package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayingCardsTest {
    @Test
    fun `카드 목록 - 추가 테스트`() {
        // given
        val playingCards = PlayingCards()

        // when
        playingCards.add(PlayingCard(Suit.CLUBS, Denomination.ACE))
        val expected = PlayingCards.of(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // then
        assertThat(playingCards).isEqualTo(expected)
    }

    @Test
    fun `카드 목록 - 추가 실패 예외처리 테스트, 카드를 중복 입력하는 경우`() {
        // given
        val playingCards = PlayingCards.of(PlayingCard(Suit.CLUBS, Denomination.ACE))

        // when
        val exception = assertThrows<IllegalArgumentException> {
            playingCards.add(PlayingCard(Suit.CLUBS, Denomination.ACE))
        }

        // then
        assertThat(exception.message).isEqualTo("중복된 카드는 추가할 수 없습니다.")
    }

    @Test
    fun `카드 목록 - 삭제 테스트`() {
        // given
        val playingCards = PlayingCards.of(PlayingCard(Suit.SPADES, Denomination.ACE))

        // when
        playingCards.get()

        // then
        assertThat(playingCards.size()).isEqualTo(0)
    }

    @Test
    fun `카드 목록 - 삭제 실패 예외처리 테스트, 카드가 없는 경우에 삭제하는 경우`() {
        // given
        val playingCards = PlayingCards()

        // when
        val exception = assertThrows<NoSuchElementException> {
            playingCards.get()
        }

        // then
        assertThat(exception.message).isEqualTo("카드가 없습니다.")
    }

    @ParameterizedTest
    @CsvSource(value = ["ACE,JACK,QUEEN:false", "TWO,JACK,QUEEN:true"], delimiter = ':')
    fun `카드 목록 - Bust 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards.of(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isBust()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["ACE,JACK,QUEEN:false", "ACE:false", "ACE,ACE:false", "ACE,TEN:true", "ACE,JACK:true", "ACE,QUEEN:true", "ACE,KING:true"], delimiter = ':')
    fun `카드 목록 - BlackJack 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards.of(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isBlackjack()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["ACE,JACK,QUEEN:true", "TWO,JACK,QUEEN:false"], delimiter = ':')
    fun `카드 목록 - Stay 여부 확인 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCards = PlayingCards.of(denominations.map { PlayingCard(Suit.SPADES, it) })

        // when
        val actual = playingCards.isStay()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
