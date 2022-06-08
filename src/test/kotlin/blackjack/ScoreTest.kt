package blackjack

import blackjack.domain.BlackJack
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Score
import blackjack.dto.BlackJackRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScoreTest : FreeSpec({

    "run" - {

        "플레이어의 점수를 계산한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(Card("다이아몬드", "2"), Card("다이아몬드", "3"))
            val cardDeck = CardDeck.Fake(cards)

            BlackJack(dto, cardDeck)

            val players = dto.players
            val score = Score(players)
            score.run()

            score.playerScore[0].player.name shouldBe players[0].name
            score.playerScore[0].score shouldBe 5
        }
    }
})
