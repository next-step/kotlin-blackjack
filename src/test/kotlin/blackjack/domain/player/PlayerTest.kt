package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가지고 있다`() {
        val player = Player(name = Name("Seongmo"))
        assertThat(player.name).isEqualTo(Name("Seongmo"))
    }

    @Test
    fun `플레이어는 카드를 가지고 있지 않을 수 있다`() {
        val player = Player(name = Name("Seongmo"))
        assertThat(player.cards).isEmpty()
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.FIVE, CardShape.CLUB)
        player.receiveCard(card)
        assertThat(player.cards.contains(card)).isTrue()
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 점수가 계산된다 (Ace는 1점으로 계산된다)`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.ACE, CardShape.CLUB)
        player.receiveCard(card)
        assertThat(player.score).isEqualTo(card.character.score)
        assertThat(player.score).isEqualTo(1)
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 총합 점수가 계산된다`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.FIVE, CardShape.CLUB)
        val card2 = Card(CardCharacter.ACE, CardShape.CLUB)
        player.receiveCard(card)
        player.receiveCard(card2)
        assertThat(player.score).isEqualTo(card.character.score + card2.character.score)
    }
}
