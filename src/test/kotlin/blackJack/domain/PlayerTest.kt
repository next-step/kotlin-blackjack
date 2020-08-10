package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun make_player() {
        val player = Player("joohan")

        assertThat(player.name).isEqualTo("joohan")
        assertThat(player.hands.size).isEqualTo(0)
    }

    @Test
    fun give_card() {
        val player = Player("joohan")

        player.giveCard(Card("♠10", 10))

        assertThat(player.hands.size).isEqualTo(1)
        assertThat(player.hands[0]).isEqualTo(Card("♠10", 10))
    }

    @Test
    fun get_score() {
        val player = Player("joohan")
        player.giveCard(Card("♠10", 10))
        player.giveCard(Card("♠10", 10))

        assertThat(player.totalScore).isEqualTo(20)
    }

    @Test
    fun player_is_over_21() {
        val player = Player("joohan")
        player.giveCard(Card("♠10", 10))
        player.giveCard(Card("♠10", 10))
        player.giveCard(Card("♠10", 10))

        assertThat(player.isBust()).isTrue()
    }
    
    @Test
    fun player_name_is_null() {
        assertThatThrownBy {
            Player(null)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 공백값과 null값을 받을수 없습니다.")
    }

    @Test
    fun player_name_is_blank() {
        assertThatThrownBy {
            Player("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 공백값과 null값을 받을수 없습니다.")
    }
}
