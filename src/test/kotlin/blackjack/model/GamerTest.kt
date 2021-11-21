package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GamerTest {

    private lateinit var gamer: Gamer

    @BeforeEach
    fun setup() {
        gamer = Player.ready(Name.valueOf("laco"))
    }

    @Test
    fun `Gamer는 처음 Ready 상태가 된다`() {
        assertThat(gamer.isReady()).isTrue
    }

    @Test
    fun `Gamer는 카드를 두 장 받으면 Running 상태가 된다`() {
        assertThat(
            gamer
                .draw(Card(Denomination.TWO, Suit.HEART))
                .draw(Card(Denomination.THREE, Suit.HEART))
                .isRunning()
        ).isTrue
    }
}
