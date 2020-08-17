package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `isAce() ACE이면 true`() {
        assertThat(Card.CLUB_ACE.isAce()).isTrue()
        assertThat(Card.DIAMOND_ACE.isAce()).isTrue()
        assertThat(Card.HEART_ACE.isAce()).isTrue()
        assertThat(Card.SPADE_ACE.isAce()).isTrue()
    }

    @Test
    fun `isAce() ACE가 아니면 false`() {
        assertThat(Card.CLUB_10.isAce()).isFalse()
        assertThat(Card.DIAMOND_Q.isAce()).isFalse()
        assertThat(Card.HEART_J.isAce()).isFalse()
        assertThat(Card.SPADE_K.isAce()).isFalse()
    }
}
