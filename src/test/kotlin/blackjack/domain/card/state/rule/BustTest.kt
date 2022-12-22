package blackjack.domain.card.state.rule

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BustTest {
    @ParameterizedTest
    @CsvSource(value = ["ACE,TEN:false", "ACE,TEN,JACK:false", "ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN:true"], delimiter = ':')
    fun `버스트 - 생성 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val hit = Hit(PlayingCards(denominations.map { PlayingCard(Suit.CLUBS, it) }))

        // when
        val actual = hit.cards.isBust()

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["ACE,TWO,THREE,FOUR,FIVE,SIX", "TEN", "TEN,JACK", "ACE,TEN", "ACE,TEN,JACK"])
    fun `버스트 - 생성 예외처리 테스트`(given: String) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCardList = denominations.map { PlayingCard(Suit.CLUBS, it) }

        // when
        val exception = assertThrows<IllegalArgumentException> {
            Bust(PlayingCards(playingCardList))
        }

        // then
        Assertions.assertThat(exception.message).isEqualTo("버스트가 아닙니다.")
    }
}
