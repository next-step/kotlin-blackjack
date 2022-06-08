package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckImpl.Companion.ACE
import blackjack.domain.card.CardDeckImpl.Companion.DIAMOND
import blackjack.domain.card.CardDeckImpl.Companion.JACK
import blackjack.domain.card.CardDeckImpl.Companion.KING
import blackjack.domain.game.BlackJack
import blackjack.domain.score.Score
import blackjack.domain.score.Score.Companion.ACE_DEFAULT_SCORE
import blackjack.domain.score.Score.Companion.ACE_SCORE
import blackjack.domain.score.Score.Companion.JACK_SCORE
import blackjack.domain.score.Score.Companion.KING_SCORE
import blackjack.dto.BlackJackRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScoreTest : FreeSpec({

    "run" - {

        "플레이어의 점수를 계산한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3")
            )
            val cardDeck = CardDeck.Fake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players
            val score = Score()
            score.run(players)

            score.playerScore[0].player.name shouldBe players[0].name
            score.playerScore[0].score shouldBe 5
        }

        "ACE카드가 존재하면서 21 초과인 경우 에이스를 1로 계산한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(DIAMOND, JACK), Card(DIAMOND, KING))
            val cardDeck = CardDeck.Fake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players
            val score = Score()
            score.run(players)

            score.playerScore[0].score shouldBe (ACE_SCORE + JACK_SCORE + KING_SCORE)
        }

        "ACE카드가 존재하면서 21 이하인 경우 에이스를 11로 계산한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(DIAMOND, JACK))
            val cardDeck = CardDeck.Fake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players
            val score = Score()
            score.run(players)

            score.playerScore[0].score shouldBe (ACE_DEFAULT_SCORE + JACK_SCORE)
        }
    }
})
