package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardKind
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSet
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = player {
            name("cookie")
        }
        player.name shouldBe "cookie"
    }

    @Test
    fun `플레이어의 이름이 없는 경우 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> {
            player {
                name("")
            }
        }
    }

    @Test
    fun `플레이어는 여러장의 카드를 가진다`() {
        val cardSet = CardSet(
            listOf(
                Card(CardKind.DIAMOND, CardNumber.ACE),
                Card(CardKind.DIAMOND, CardNumber.ACE)
            )
        )

        val player = player {
            name("cookie")
            cardSet(cardSet)
        }
        player.cardSet shouldBe cardSet
    }
}


