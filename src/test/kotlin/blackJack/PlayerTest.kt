package blackJack

import card.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import player.Player
import player.Status

class PlayerTest {

    @Test
    fun `,플에이어가 생성될 때 ,초기 상태는 플레이중 이다 `() {
        // given :

        // when : 플레이어가 생성될 때
        val player = Player("OYJ")
        val actual = player.status

        // then : 초기 상태는  PLAYING이다.
        val expect = Status.PLAYING
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플에이어가 생성되고, 카드 받기가 종료될 때, 플에이어 상태는 STAND가 된다`() {
        // given : 플레이어가 생성된다.
        val player = Player("OYJ")

        // when : 플레이어가 카드 받기를 멈춘다.
        player.hitDone()
        val actual = player.status

        // then : 플레이어의 상태는 STAND이다.
        val expect = Status.STAND
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어와 카드팩이 생성되고, 플레이어가 카드를 받을 때, 받은 카드는 저장한다`() {
        // given : 플레이어와 카드팩을 생성한다.
        val player = Player("OYJ")
        val cardPack = CardPack.getCardPack()
        val card = cardPack.cardList[0]

        // when : 플레이어가 카드를 받는다.
        player.saveCard(card)
        val actual = player.cardList[0]

        // then :
        assertThat(actual).isEqualTo(card)
    }
}
