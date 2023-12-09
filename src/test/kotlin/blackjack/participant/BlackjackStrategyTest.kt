package blackjack.participant

import blackjack.game.ScoreCalculator
import blackjack.card.AceCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.PictureCard
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Bust
import blackjack.participant.status.Hit
import blackjack.participant.status.Stand
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackStrategyTest {

    @Test
    fun `처음 두 장을 드로우 했을 때 합이 21이면 블랙잭상태이다`() {
        val blackjackStrategy = BlackjackStrategy(ScoreCalculator())
        blackjackStrategy.drawCard(
            listOf(
                PictureCard(CardPicture.JACK, CardPattern.SPADE),
                AceCard(CardPattern.SPADE),
            )
        )

        blackjackStrategy.status.shouldBeInstanceOf<Blackjack>()
    }

    @Test
    fun `처음 두 장을 드로우 했을 때 합이 21이 아니면 히트 상태이다`() {
        val blackjackStrategy = BlackjackStrategy(ScoreCalculator())
        blackjackStrategy.drawCard(
            listOf(
                PictureCard(CardPicture.JACK, CardPattern.SPADE),
                PictureCard(CardPicture.JACK, CardPattern.SPADE)
            )
        )

        blackjackStrategy.status.shouldBeInstanceOf<Hit>()
    }

    @Test
    fun `합이 21미만이고 더 이상 카드를 뽑지 않으면 스탠드 상태이다`() {
        val blackjackStrategy = BlackjackStrategy(ScoreCalculator())
        blackjackStrategy.drawCard(
            listOf(
                PictureCard(CardPicture.JACK, CardPattern.SPADE)
            )
        )

        blackjackStrategy.status.shouldBeInstanceOf<Stand>()
    }

    @Test
    fun `합이 21이상이면 버스트 상태이다`() {
        val blackjackStrategy = BlackjackStrategy(ScoreCalculator())
        blackjackStrategy.drawCard(
            listOf(
                PictureCard(CardPicture.JACK, CardPattern.SPADE),
                PictureCard(CardPicture.JACK, CardPattern.SPADE),
                PictureCard(CardPicture.JACK, CardPattern.SPADE)
            )
        )

        blackjackStrategy.status.shouldBeInstanceOf<Bust>()
    }
}
