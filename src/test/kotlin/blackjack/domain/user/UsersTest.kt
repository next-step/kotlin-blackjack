package blackjack.domain.user

import blackjack.domain.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UsersTest {
    @Test
    fun `유저가 한명도 없을경우 IllegalArgumentException을 던진다`() {
        assertThrows<IllegalArgumentException> {
            Users(listOf(), Deck(), Dealer(Deck().takeCard()))
        }
    }

    @Test
    internal fun `게임 시작시 카드를 2장씩 받는다`() {
        assertThat(Users.of(listOf("link", "jason"), Deck()).users[0].cards.getSize()).isEqualTo(2)
    }
}
