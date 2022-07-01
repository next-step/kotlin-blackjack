package game.blackjack.domain

import game.blackjack.view.ResultView
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어 카드 분배")
internal class PlayersTest {

    @Test
    fun `초기화하면 2장의 카드를 갖는다`() {
        val players = Players(Dealer(), listOf(Player("jade"), Player("sehee")))
        players.init(2)
        players.forEach { Assertions.assertThat(it.cards.size()).isEqualTo(2) }
    }

    @Test
    fun `상태가 버스트로 바뀔때까지 받는다`() {
        val players = Players(Dealer(), listOf(Player("jade"), Player("sehee")))
        players.init(2)
        players.distribute({ true }, { ResultView().printPlayerCard(it) })
        players.forEach { Assertions.assertThat(it.isBust()).isTrue() }
    }

    @Test
    fun `카드를 안받으면 2장의 카드 그대로이다`() {
        val players = Players(Dealer(), listOf(Player("jade"), Player("sehee")))
        players.init(2)
        players.distribute({ false }, { ResultView().printPlayerCard(it) })
        players.forEach { Assertions.assertThat(it.isBust()).isFalse() }
        players.forEach { Assertions.assertThat(it.cards.size()).isEqualTo(2) }
    }
}
