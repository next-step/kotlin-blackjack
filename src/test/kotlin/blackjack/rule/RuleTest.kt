package blackjack.rule

import blackjack.card.CardFixture
import blackjack.card.Rank
import blackjack.dealer.Dealer
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.player.Players
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RuleTest {
    @Test
    fun `딜러의 패의 합이 21미만이면서 모든 플레이어의 패 합이 21을 넘지 않으면 게임을 진행한다`() {
        val players =
            Players(
                listOf(
                    Player(name = "pobi", hand = Hand(progressCardSet)),
                    Player(name = "jason", hand = Hand(bustCardSet))
                ),
            )

        val dealer = Dealer.ready(initialCards = progressCardSet)

        Rule.isGameActive(players = players, dealer = dealer) shouldBe true
    }

    @Test
    fun `모든 플레이어의 패 합이 21을 초과하면 게임을 종료한다`() {
        val players =
            Players(
                listOf(
                    Player(name = "pobi", hand = Hand(bustCardSet)),
                    Player(name = "jason", hand = Hand(bustCardSet))
                ),
            )

        val dealer = Dealer.ready(initialCards = progressCardSet)

        Rule.isGameActive(players = players, dealer = dealer) shouldBe false
    }

    @Test
    fun `딜러의 패의 합이 21을 초과하면 게임을 종료한다`() {
        val players =
            Players(
                listOf(
                    Player(name = "pobi", hand = Hand(progressCardSet)),
                    Player(name = "jason", hand = Hand(progressCardSet))
                ),
            )

        val dealer = Dealer.ready(initialCards = bustCardSet)

        Rule.isGameActive(players = players, dealer = dealer) shouldBe false
    }
    
    companion object {
        private val progressCardSet = listOf(
            CardFixture.generateTestCard(Rank.TEN),
            CardFixture.generateTestCard(Rank.FIVE),
            CardFixture.generateTestCard(Rank.SIX),
        )

        private val bustCardSet = listOf(
            CardFixture.generateTestCard(Rank.TEN),
            CardFixture.generateTestCard(Rank.FIVE),
            CardFixture.generateTestCard(Rank.SEVEN),
        )
    }
}
