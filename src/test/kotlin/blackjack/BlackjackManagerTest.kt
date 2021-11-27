package blackjack

import blackjack.domain.BlackJackManager
import blackjack.domain.CardDeck
import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.domain.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `giveInitialCards()을 통해 cardDeck으로 부터 초기 2장의 카드를 나눠줄 수 있다`() {
        val cardDeck = CardDeck()
        val players = Players.of(Player(Name.from("pobi")), Player(Name.from("jason")))
        val blackJackManager = BlackJackManager(players)

        blackJackManager.giveInitialCards(cardDeck)

        players.forEach { player ->
            assertThat(player.cards.size).isEqualTo(2)
        }
    }
}
