package blackJack.player

import card.CardPack
import card.CardRank
import card.PlayingCard
import card.Suit
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
        player.playDone()
        val actual = player.status

        // then : 플레이어의 상태는 STAND이다.
        val expect = Status.STAND
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어와 카드팩이 생성되고, 플레이어가 카드를 받을 때, 받은 카드는 저장한다`() {
        // given : 플레이어와 카드팩을 생성한다.
        val player = Player("OYJ")
        val card = CardPack.cards[0]

        // when : 플레이어가 카드를 받는다.
        player.saveCard(card)
        val actual = player.cardList[0]

        // then :
        assertThat(actual).isEqualTo(card)
    }

    @Test
    fun `플레이어의 보유 가드 합이 20이하 이고, 플레이어 상태 업데이트를 요청할 때, 플에이어 상테는 PLAYING로 업데이트 된다`() {
        // given : 플레이어 보유 카드 합 20 이하(8)
        val player = Player("OYJ")
        player.saveCard(PlayingCard(Suit.DIAMOND, CardRank.TREE))
        player.saveCard(PlayingCard(Suit.DIAMOND, CardRank.FIVE))

        // when : 플레이어 상태 업데이트
        player.updateStatus()
        val actual = player.status

        // then : 플레이어 상태는 PLAYING이다.
        assertThat(actual).isEqualTo(Status.PLAYING)
    }

    @Test
    fun `플레이어의 보유 가드 합이 21이상 이고, 플레이어 상태 업데이트를 요청할 때, 플에이어 상테는 STAND로 업데이트 된다`() {
        // given : 플레이어 보유 카드 합 21 이상(28)
        val player = Player("OYJ")
        player.saveCard(PlayingCard(Suit.DIAMOND, CardRank.EIGHT))
        player.saveCard(PlayingCard(Suit.DIAMOND, CardRank.KING))
        player.saveCard(PlayingCard(Suit.DIAMOND, CardRank.JACK))

        // when : 플레이어 상태 업데이트
        player.updateStatus()
        val actual = player.status

        // then : 플레이어 상태는 PLAYING이다.
        assertThat(actual).isEqualTo(Status.STAND)
    }

    @Test
    fun `, 플레이어가 플레이를 멈춘다고 할때, 플에이어의 상태는 STAND로 업데이트 된다`() {
        // given : 플레이어 보유 카드 합 21 이상(28)
        val player = Player("OYJ")

        // when : 플레이어 상태 업데이트
        player.playDone()
        val actual = player.status

        // then : 플레이어 상태는 PLAYING이다.
        assertThat(actual).isEqualTo(Status.STAND)
    }
}
