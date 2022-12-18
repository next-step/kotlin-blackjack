package blackjack.domain.card.state.rule

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class StayTest {
    @ParameterizedTest
    @CsvSource(value = ["ACE,TEN,JACK:true", "ACE,TWO,THREE,FOUR,FIVE,SIX:true"], delimiter = ':')
    fun `스테이 - 생성 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val hit = Hit(PlayingCards.of(denominations.map { PlayingCard(Suit.CLUBS, it) }))

        // when
        val actual = hit.cards.isStay()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN", "TEN"])
    fun `스테이 - 생성 예외처리 테스트`(given: String) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCardList = denominations.map { PlayingCard(Suit.CLUBS, it) }

        // when
        val exception = assertThrows<IllegalArgumentException> {
            Stay(PlayingCards.of(playingCardList))
        }

        // then
        assertThat(exception.message).isEqualTo("스테이가 아닙니다.")
    }
}
