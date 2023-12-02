package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import io.kotest.matchers.shouldBe
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
        assertThat(player.getCards()).isEmpty()
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.FIVE, CardShape.CLUB)
        player.receiveCard(card)
        assertThat(player.getCards().contains(card)).isTrue()
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 점수가 계산된다 (Ace는 사용자가 21점 미만에서는 11점으로 계산된다)`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.ACE, CardShape.CLUB)
        player.receiveCard(card)
        assertThat(player.state.hands.score().value).isEqualTo(11)
    }

    @Test
    fun `플레이어가 가지고 있는 카드의 총합 점수가 계산된다`() {
        val player = Player(name = Name("Seongmo"))
        val card = Card(CardCharacter.FIVE, CardShape.CLUB)
        val card2 = Card(CardCharacter.TEN, CardShape.CLUB)
        player.receiveCard(card)
        player.receiveCard(card2)
        assertThat(player.state.hands.score().value).isEqualTo(card.character.score + card2.character.score)
    }

    @Test
    fun `플레이어의 카드 상태가 블랙잭 상태면 Finished 상태다`() {
        val player = Player(name = Name("Seongmo"))
        player.receiveCard(Card(CardCharacter.TEN, CardShape.CLUB))
        player.receiveCard(Card(CardCharacter.ACE, CardShape.CLUB))
        player.isFinished() shouldBe true
    }
}
