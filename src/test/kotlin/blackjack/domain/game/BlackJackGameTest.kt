package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardKind
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSet
import blackjack.domain.player.player
import blackjack.domain.player.playerGroup
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `블랙잭 카드 게임이 시작하면 2장의 카드를 플레이어게 지급한다`() {
        val blackJackGame = BlackJackGame()
        val playerGroup = playerGroup {
            players(player { name("jack") }, player { name("john") })
        }
        val cardDealer = object : CardDealer {
            override fun selectCard(): Card {
                return Card.of(CardKind.DIAMOND, CardNumber.JACK)
            }

            override fun selectCard(count: Int): CardSet {
                return CardSet.of(
                    Card.of(CardKind.DIAMOND, CardNumber.JACK), Card.of(CardKind.DIAMOND, CardNumber.QUEEN)
                )
            }
        }

        val actual = blackJackGame.dealCard(cardDealer, playerGroup, BlackJackGame.START_CARD_COUNT)
        actual.players.forAll {
            it.cardSet.size shouldBe 2
        }
    }
}
