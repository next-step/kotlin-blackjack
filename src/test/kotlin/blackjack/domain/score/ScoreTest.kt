package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck.Companion.ACE
import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import blackjack.domain.card.RandomCardDeck.Companion.JACK
import blackjack.domain.card.RandomCardDeck.Companion.KING
import blackjack.domain.game.BlackJack
import blackjack.dto.BlackJackRequest
import blackjack.util.CardDeckFake
import blackjack.view.InputPlayerBetting
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScoreTest : FreeSpec({

    "calculate" - {

        "플레이어의 점수를 계산한다." {
            val dto = BlackJackRequest.of(listOf(InputPlayerBetting("uju", "1000")))
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3"),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players

            val scoreResult = Score.calculatePlayerScore(players[0])

            scoreResult.player.name shouldBe players[0].name
            scoreResult.score shouldBe 5
        }

        "ACE카드가 존재하면서 21 초과인 경우 에이스를 1로 계산한다." {
            val dto = BlackJackRequest.of(listOf(InputPlayerBetting("uju", "1000")))
            val cards = mutableListOf(
                Card(DIAMOND, ACE),
                Card(DIAMOND, JACK),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
                Card(DIAMOND, KING),
            )
            val cardDeck = CardDeckFake(cards)

            val blackJack = BlackJack(dto, cardDeck)
            val players = dto.players
            blackJack.giveCard(players[0])

            val scoreResult = Score.calculatePlayerScore(players[0])
            scoreResult.score shouldBe 21
        }

        "ACE카드가 존재하면서 21 이하인 경우 에이스를 11로 계산한다." {
            val dto = BlackJackRequest.of(listOf(InputPlayerBetting("uju", "1000")))
            val cards = mutableListOf(
                Card(DIAMOND, ACE),
                Card(DIAMOND, JACK),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players

            val scoreResult = Score.calculatePlayerScore(players[0])
            scoreResult.score shouldBe 21
        }
    }
})
