package blackjack.domain.card.state.rule

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `히트 - 드로우 테스트, Bust 객체 반환 확인`() {
        // given
        val playingCards = PlayingCards(
            listOf(
                PlayingCard(Suit.CLUBS, Denomination.TEN),
                PlayingCard(Suit.CLUBS, Denomination.JACK)
            )
        )
        val hit = Hit(playingCards)

        // when
        val actual = hit.draw(PlayingCard(Suit.CLUBS, Denomination.TWO))

        // then
        assertTrue(actual is Bust)
    }

    @Test
    fun `히트 - 드로우 테스트, Hit 객체 반환 확인`() {
        // given
        val playingCards = PlayingCards(
            listOf(
                PlayingCard(Suit.CLUBS, Denomination.ACE),
                PlayingCard(Suit.CLUBS, Denomination.TWO)
            )
        )
        val hit = Hit(playingCards)

        // when
        val actual = hit.draw(PlayingCard(Suit.CLUBS, Denomination.THREE))

        // then
        assertTrue(actual is Hit)
    }

    @Test
    fun `히트 - 스테이 테스트, Stay 객체 반환 확인`() {
        // given
        val playingCards = PlayingCards(
            listOf(
                PlayingCard(Suit.CLUBS, Denomination.ACE),
                PlayingCard(Suit.CLUBS, Denomination.TEN)
            )
        )
        val hit = Hit(playingCards)

        // when
        val actual = hit.stay()

        // then
        assertTrue(actual is Stay)
    }
}
