package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class BlackJackTest {
    @Test
    fun `블랙잭은 딜러, 플레이어와 카드 덱을 가진다`() {
        val blackJack = BlackJack(
            listOf(
                Player("player1"),
                Player("player2")
            )
        )

        assertThat(blackJack.deck).isInstanceOf(CardDeck::class.java)
        assertThat(blackJack.dealer).isInstanceOf(Dealer::class.java)
        blackJack.players.forEach {
            assertThat(it).isInstanceOf(Player::class.java)
        }
    }

    @Test
    fun `동일한 이름의 플레이어를 받으면 에러를 일으킨다`() {
        assertThrows<IllegalArgumentException> {
            BlackJack(
                listOf(
                    Player("player1"),
                    Player("player1")
                )
            )
        }
    }

    @Test
    fun `최소 두 명 이상의 플레이어를 받지 않으면 에러를 일으킨다`() {
        assertThrows<IllegalArgumentException> {
            BlackJack(listOf(Player("player1")))
        }
    }

    @Test
    fun `게임을 시작하면 각 플레이어와 딜러에게 두 장의 카드를 지급한다`() {
        val blackJack = BlackJack(
            listOf(
                Player("player1"),
                Player("player2")
            )
        )

        assertThat(blackJack.deck.cards.size).isEqualTo(46)
        assertThat(blackJack.dealer.cards.size).isEqualTo(2)
        assertThat(blackJack.players).allMatch { it.cards.size == 2 }
    }

    @Test
    fun `한 플레이어가 새로운 카드 한 장을 받을 수 있다`() {
        val blackJack = BlackJack(
            listOf(
                Player("player1"),
                Player("player2")
            )
        )
        val player = blackJack.players.first()

        blackJack.drawAnotherCard(player)

        assertThat(player.cards.size).isEqualTo(3)
    }
}
