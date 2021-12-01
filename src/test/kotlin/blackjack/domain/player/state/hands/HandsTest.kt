package blackjack.domain.player.state.hands

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.util.PlayerStateTestFixture.createHands
import blackjack.error.DuplicatePlayingCardException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("패(Hands)")
internal class HandsTest {

    @Test
    fun `비어있을 수 있다`() {
        val hands = Hands.EMPTY

        assertAll(
            { assertThat(hands).isNotNull },
            { assertThat(hands).isExactlyInstanceOf(Hands::class.java) },
        )
    }

    @Test
    fun `새로운 카드를 추가할 수 있다`() {
        val extraCard = Card(Suit.CLUB, Denomination.ACE)
        val expected = Hands.from(listOf(extraCard))

        val hands = Hands.EMPTY
            .draw(extraCard)

        assertThat(hands).isEqualTo(expected)
    }

    @Test
    fun `새로운 카드를 추가할 때 중복된 카드는 들어올 수 없다`() {
        val extraCard = Card(Suit.CLUB, Denomination.ACE)
        val hands = Hands.EMPTY
            .draw(extraCard)

        val exception = assertThrows<DuplicatePlayingCardException> { hands.draw(extraCard) }
        assertThat(exception.message).isEqualTo("이미 덱에 존재하는 카드가 있습니다.")
    }

    @Test
    fun `카드들의 점수 합을 반환한다`() {
        val hands = createHands(
            Suit.CLUB,
            Denomination.ACE,
            Denomination.TWO,
            Denomination.THREE,
            Denomination.FOUR,
            Denomination.FIVE,
            Denomination.SIX
        )

        val score = hands.score()
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `패에 ACE가 있고 나머지 점수가 10이하라면 21에 가까운 수를 반환한다`() {
        val playingCards = createHands(Suit.CLUB, Denomination.ACE, Denomination.TEN)

        val score = playingCards.score()
        assertThat(score.score).isEqualTo(21)
    }
}
