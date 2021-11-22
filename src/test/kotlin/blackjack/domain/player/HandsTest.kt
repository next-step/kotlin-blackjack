package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.hands.Hands
import blackjack.error.DuplicatePlayingCardException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("카드들(Cards)")
internal class HandsTest {

    @Test
    fun `카드들에는 빈값이 들어올 수 있다`() {
        val hands: Hands = Hands.EMPTY

        assertAll(
            { assertThat(hands).isNotNull },
            { assertThat(hands).isExactlyInstanceOf(Hands::class.java) },
        )
    }

    @Test
    fun `카드들에 새로운 카드가 추가될 수 있다`() {
        val externalPlayingCards = allPlayingCards()
        val expected = Hands.from(listOf(externalPlayingCards[0]))

        val hands = Hands.EMPTY
            .draw(externalPlayingCards[0])

        assertThat(hands).isEqualTo(expected)
    }

    @Test
    fun `카드들에 새로운 카드들이 추가될 때 중복된 카드가 들어오면 예외를 발생한다`() {
        val externalPlayingCards = allPlayingCards()
        val hands = Hands.EMPTY
            .draw(externalPlayingCards[0])

        val exception = assertThrows<DuplicatePlayingCardException> { hands.draw(externalPlayingCards[0]) }
        assertThat(exception.message).isEqualTo("이미 덱에 존재하는 카드가 있습니다.")
    }

    @Test
    fun `카드들은 점수의 합을 반환한다`() {
        val externalPlayingCards = allPlayingCards()
        val hands = Hands.EMPTY
            .draw(externalPlayingCards[0])
            .draw(externalPlayingCards[1])
            .draw(externalPlayingCards[2])
            .draw(externalPlayingCards[3])
            .draw(externalPlayingCards[4])
            .draw(externalPlayingCards[5])

        val expected = externalPlayingCards
            .subList(0, 6)
            .map { it.score() }
            .reduce { x, y -> x + y }

        val score = hands.score()
        assertThat(score.score).isEqualTo(expected.score)
    }

    @Test
    fun `ACE 는 21애 가까운 수가 되도록 11을 반환한다`() {
        val playingCards = Hands.from(
            listOf(
                Card(Suit.CLUB, Denomination.ACE),
                Card(Suit.CLUB, Denomination.TEN),
            )
        )
        val score = playingCards.score()
        assertThat(score.score).isEqualTo(21)
    }

    companion object {
        fun allPlayingCards(): List<Card> = Suit.values().flatMap { suit: Suit ->
            Denomination.values().map { denomination ->
                Card(suit, denomination)
            }
        }
    }
}
