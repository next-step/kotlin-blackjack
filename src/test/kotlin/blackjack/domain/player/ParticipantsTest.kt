package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.rule.Score
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러가 21을 초과하면 카드 결과에 상관없이 항상 이긴다`() {
        // given playerImpl
        // given player 1
        val sampleCard = Card.createCard(CardRank.EIGHT, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.NINE, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val goofyPlayer = GamePlayer("goofy", myCards)

        // given player 2
        val sampleCard3 = Card.createCard(CardRank.FOUR, CardShape.HEART)
        val myCards2 = CardHold(mutableListOf(sampleCard3))
        val zeroPlayer = GamePlayer("zero", myCards2)

        // given dealer
        val sampleCard4 = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard5 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard6 = Card.createCard(CardRank.KING, CardShape.HEART)
        val myCards3 = CardHold(mutableListOf(sampleCard4, sampleCard5, sampleCard6))
        val dealer = Dealer(cardHold = myCards3)

        // given
        val participants = Participants(listOf(goofyPlayer, zeroPlayer), dealer)
        val result = participants.getGameResult()

        // then
        result["딜러"] shouldBe Score.init().win(cnt = 2)
    }
}
