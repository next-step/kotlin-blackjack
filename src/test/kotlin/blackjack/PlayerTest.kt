package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private val cards = Cards(Card(CardNumber.Ace, Suit.Heart), Card(CardNumber.Two, Suit.Heart))
    private val player = Player("jason", cards)

    @Test
    fun `이름과 Cards 를 속성으로 갖는다`() {
        assertThat(player.name).isEqualTo("jason")
        assertThat(player.cards).isEqualTo(cards)
    }

    @Test
    fun `지급받은 카드들의 숫자 합을 반환한다 (A는 1, 11로 대응된다)`() {
        assertThat(player.scores()).isEqualTo(listOf(Score(3), Score(13)))
    }

    @Test
    fun `게임 종료 여부를 반환한다`() {
        assertThat(player.gameOver).isFalse()
        assertThat(player.gameOver().gameOver).isTrue()
    }

    @Test
    fun `플레이어에게 카드를 지급한다`() {
        val newPlayer = player.addCards(listOf(Card(CardNumber.Five, Suit.Heart), Card(CardNumber.Six, Suit.Heart)))
        assertThat(newPlayer.cards.values.size).isEqualTo(4)
    }
}
