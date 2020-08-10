package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `게임 참가 시 2장의 카드 받기`() {
        val player = Player("moshi").apply {
            requestCard(Card.pop())
            requestCard(Card.pop())
        }

        assertThat(player.myReceivedCard.size).isEqualTo(2)
    }

    @Test
    fun `점수 계산하기`() {
        val player = Player("moshi").apply {
            requestCard(Card(Kinds.TWO, Shape.HEART))
            requestCard(Card(Kinds.EIGHT, Shape.SPADE))
            requestCard(Card(Kinds.ACE, Shape.CLOVER))
        }

        assertThat(player.calculateRank()).isEqualTo(21)
    }
}
