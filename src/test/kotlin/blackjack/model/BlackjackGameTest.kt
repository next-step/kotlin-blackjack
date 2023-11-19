package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.strategy.NormalStrategy
import blackjack.view.OutputView
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `딜러, 플레이어의 이름, 카드 셔플 규칙 정보를 받아 게임을 생성한다`() {
        val dealer = Dealer(CardDeck(NormalStrategy()))
        val player = listOf(Player(name = "jason"), Player(name = "pobi"))
        val game = BlackjackGame(dealer, player, OutputView())

        game.shouldBeInstanceOf<BlackjackGame>()
    }
}
