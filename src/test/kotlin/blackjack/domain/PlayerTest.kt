package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest{

    @ParameterizedTest
    @ValueSource(strings = ["", "  "])
    fun `플레이어 이름으로 빈 값 전달시 예외 발생`(name: String){
        assertThatThrownBy { Player(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어 이름은 빈 값일 수 없습니다.")
    }

    @Test
    fun `주어진 카드를 저장한다`() {
        val player = Player("test")
        val card = Card(CardSuitInfo.SPADE, CardNumberInfo.ACE)
        player.addCard(card)

        assertThat(player.getCards().size).isEqualTo(1)
        assertThat(player.getCards()).contains(card)
    }
}
