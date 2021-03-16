package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `create not ace card test`() {

        // GIVEN
        val diamond = CardType.DIAMOND
        val five = CardNumber.FIVE

        // WHEN
        val card = Card(diamond, five)

        // THEN
        assertThat(card.toString()).isEqualTo("${five.description}${diamond.description}")
        assertThat(card.isAce).isFalse()
    }

    @Test
    fun `create ace card test`() {

        // GIVEN
        val club = CardType.CLUB
        val ace = CardNumber.ACE

        // WHEN
        val card = Card(club, ace)

        // THEN
        assertThat(card.toString()).isEqualTo("${ace.description}${club.description}")
        assertThat(card.isAce).isTrue()
    }
}
