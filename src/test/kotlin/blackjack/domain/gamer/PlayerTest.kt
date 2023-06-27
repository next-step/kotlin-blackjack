package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.heartCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "카드를 삽입 했다면 카드 목록이 비어있지 않다" {
        val player = Player(PlayerName("test"))
        val card = heartCard(CardDenomination.ACE)
        player.pass(card)
        player.hasCard() shouldBe true
    }

    "카드를 삽입하지 않았다면 카드 목록이 비어있다" {
        val player = Player(PlayerName("test"))
        player.hasCard() shouldBe false
    }

    "다수의 카드를 삽입할 수 있다" {
        val cards = Card.ALL_CARDS
        val player = Player(PlayerName("test")).apply {
            pass(cards)
        }
        player.cards shouldBe cards
    }
})
