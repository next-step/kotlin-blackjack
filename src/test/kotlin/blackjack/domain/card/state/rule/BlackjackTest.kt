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

class BlackjackTest {
    @ParameterizedTest
    @CsvSource(value = ["ACE,TEN:true", "ACE,JACK:true", "ACE,QUEEN:true", "ACE,KING:true", "TWO,KING:false"], delimiter = ':')
    fun `블랙잭 - 생성 테스트`(given: String, expected: Boolean) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val hit = Hit(PlayingCards(denominations.map { PlayingCard(Suit.CLUBS, it) }))

        // when
        val actual = hit.cards.isBlackjack()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["ACE,ACE", "TEN,JACK", "ACE,JACK,QUEEN"])
    fun `블랙잭 - 생성 예외처리 테스트`(given: String) {
        // given
        val denominations = given.split(",").map { Denomination.valueOf(it) }
        val playingCardList = denominations.map { PlayingCard(Suit.CLUBS, it) }

        // when
        val exception = assertThrows<IllegalArgumentException> {
            Blackjack(PlayingCards(playingCardList))
        }

        // then
        assertThat(exception.message).isEqualTo("블랙잭이 아닙니다.")
    }
}
