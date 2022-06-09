package blackJackTest.player

import domain.card.Card
import domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 보유카드 정보 테스트`(){
        val player = Player("이호재")
        val spadeCard = Card(Card.Symbol.TWO,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.THREE,Card.Suit.HEART)
        player.drawCard(spadeCard,heartCard)

        assertThat(player.handsStatus()).isEqualTo("2스페이드 3하트")
    }

    @Test
    fun `플레이어 카드드로우 가능 테스트`(){
        val player = Player("이호재")
        val spadeCard = Card(Card.Symbol.TWO,Card.Suit.SPADE)
        val heartCard = Card(Card.Symbol.THREE,Card.Suit.HEART)
        assertThat(player.canDraw()).isEqualTo(true)
    }


}