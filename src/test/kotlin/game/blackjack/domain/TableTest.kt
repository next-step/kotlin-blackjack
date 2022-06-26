package game.blackjack.domain

import game.blackjack.view.ResultView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("블랙잭 테이블")
internal class TableTest {

    @Test
    fun `초기화하면 2장의 카드를 갖는다`() {
        val resultView = ResultView()
        val table = Table(
            Dealer(),
            listOf(Player("jade"), Player("sehee")),
            { true },
            { resultView.printPlayerCard(it) },
        )

        val players = table.init()
        players.forEach { assertThat(it.cards.size()).isEqualTo(2) }
    }

    @Test
    fun `무조건 카드를 받으면 상태가 버스트로 바뀔때까지 받는다`() {
        val resultView = ResultView()
        val table = Table(
            Dealer(),
            listOf(Player("jade"), Player("sehee")),
            { true },
            { resultView.printPlayerCard(it) },
        )

        table.init()
        val players = table.distribute()
        players.forEach { assertThat(it.cards.isBust()).isTrue() }
    }

    @Test
    fun `무조건 카드를 안받으면 2장의 카드 그대로이다`() {
        val resultView = ResultView()
        val table = Table(
            Dealer(),
            listOf(Player("jade"), Player("sehee")),
            { false },
            { resultView.printPlayerCard(it) },
        )

        table.init()
        val players = table.distribute()
        players.forEach { assertThat(it.cards.size()).isEqualTo(2) }
    }
}
