package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
        assertThat(player.stay).isFalse()
        assertThat(player.setStay().stay).isTrue()
    }

    @Test
    fun `플레이어에게 카드를 지급한다`() {
        val newPlayer = player.addCards(listOf(Card(CardNumber.Five, Suit.Heart), Card(CardNumber.Six, Suit.Heart)))
        assertThat(newPlayer.cards.values.size).isEqualTo(4)
    }

    @ParameterizedTest
    @CsvSource(
        "Ace,Six,Ten,Seven,true",
        "Ace,Six,Ten,Five,true",
        "Ace,Six,Ten,Eight,false",
        "Ten,Seven,Ace,Six,true",
        "Ten,Five,Ace,Six,false",
        "Ten,Eight,Ace,Six,true"
    )
    fun `다른 플레이어와 비교해 승리했는지 여부를 판단한다`(
        player1Card1: CardNumber,
        player1Card2: CardNumber,
        player2Card1: CardNumber,
        player2Card2: CardNumber,
        player1Win: Boolean
    ) {
        val player1 = Player("player1", Cards(Card(player1Card1, Suit.Heart), Card(player1Card2, Suit.Heart)))
        val player2 = Player("player2", Cards(Card(player2Card1, Suit.Heart), Card(player2Card2, Suit.Heart)))
        assertThat(player1.isWinThen(player2)).isEqualTo(player1Win)
    }
}
