package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.domain.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `CardDeck으로부터 Player들에게 카드를 각각 하나씩 나눠줄 수 있다`() {
        val players = Players.of(Player("seunghwan"), Player("Seo"))

        players.eachAcceptCards(CardDeck())

        players.forEach {
            assertThat(it.cards.count()).isEqualTo(1)
        }
    }
}
